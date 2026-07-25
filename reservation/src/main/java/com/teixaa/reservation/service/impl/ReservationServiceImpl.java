package com.teixaa.reservation.service.impl;

import com.teixaa.reservation.entity.Reservation;
import com.teixaa.reservation.entity.ReservationItem;
import com.teixaa.reservation.enums.ReservationStatus;
import com.teixaa.reservation.exception.ResourceNotFoundException;
import com.teixaa.reservation.feign.EventsClient;
import com.teixaa.reservation.feign.VenuesClient;
import com.teixaa.reservation.feign.dto.SeatFeignDto;
import com.teixaa.reservation.mapper.ReservationMapper;
import com.teixaa.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements IReservationService {

    //old

    private final ReservationRepository reservationRepository;
    private final IReservationItemService reservationItemService;
    private final ReservationMapper reservationMapper;
    private final IInventoryService inventoryService;
    private final EventsClient eventsClient;
    private final VenuesClient venuesClient;

    @Value("${reservation.ttl-minutes:15}")
    private Integer reservationTtlMinutes;

    @Override
    public CartResponseDto createCart(UUID customerId) {
        log.info("Creating cart for customer: {}", customerId);

        Reservation reservation = Reservation.builder()
                .customerId(customerId)
                .status(ReservationStatus.PENDING)
                .totalAmount(BigDecimal.ZERO)
                .expiresAt(LocalDateTime.now().plusMinutes(reservationTtlMinutes))
                .build();

        Reservation saved = reservationRepository.save(reservation);
        log.info("Cart created with id: {}", saved.getId());

        // TODO: Publish event via Kafka: reservation.created

        return reservationMapper.toCartResponse(saved);
    }

    @Override
    public CartResponseDto addItem(UUID cartId, AddItemRequestDto request) {
        log.info("Adding item to cart: {}", cartId);

        Reservation reservation = reservationRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", cartId));

        if (!reservation.getStatus().equals(ReservationStatus.PENDING)) {
            throw new IllegalStateException("Cannot add items to reservation with status: " + reservation.getStatus());
        }

        // Validate session exists and is active
        try {
            eventsClient.fetchSessionDetails(request.getEventId(), request.getSessionId());
            log.info("Session validated for event: {}, session: {}", request.getEventId(), request.getSessionId());
        } catch (Exception e) {
            log.error("Failed to validate session: {}", e.getMessage());
            throw new IllegalStateException("Invalid session or event");
        }

        // Validate seat if provided (for reserved seating)
        if (request.getSeatId() != null) {
            try {
                SeatFeignDto seat = venuesClient.getSeat(request.getSeatId());
                if (!seat.getActive()) {
                    throw new IllegalStateException("Seat is not active");
                }
                log.info("Seat validated: {}", request.getSeatId());
            } catch (Exception e) {
                log.error("Failed to validate seat: {}", e.getMessage());
                throw new IllegalStateException("Invalid seat");
            }
        }

        // Reserve inventory
        boolean reserved = inventoryService.reserveInventory(
                request.getSessionId(),
                request.getSeatId(),
                request.getQuantity()
        );

        if (!reserved) {
            throw new IllegalStateException("Unable to reserve inventory");
        }

        ReservationItem item = ReservationItem.builder()
                .eventId(request.getEventId())
                .sessionId(request.getSessionId())
                .seatId(request.getSeatId())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        reservation.addItem(item);
        reservationItemService.save(item);

        Reservation updated = reservationRepository.save(reservation);
        log.info("Item added to cart: {}", cartId);

        return reservationMapper.toCartResponse(updated);
    }

    @Override
    public CartResponseDto removeItem(UUID cartId, UUID itemId) {
        log.info("Removing item from cart: {}", itemId);

        Reservation reservation = reservationRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", cartId));

        if (!reservation.getStatus().equals(ReservationStatus.PENDING)) {
            throw new IllegalStateException("Cannot remove items from reservation with status: " + reservation.getStatus());
        }

        ReservationItem item = reservationItemService.findById(itemId);

        // Release inventory
        inventoryService.releaseInventory(
                item.getSessionId(),
                item.getSeatId(),
                item.getQuantity()
        );

        reservation.removeItem(item);
        reservationItemService.delete(item);

        Reservation updated = reservationRepository.save(reservation);
        log.info("Item removed from cart: {}", itemId);

        return reservationMapper.toCartResponse(updated);
    }

    @Override
    public ReservationResponseDto checkout(UUID cartId, CheckoutRequestDto request) {
        log.info("Checkout cart: {}", cartId);

        Reservation reservation = reservationRepository.findByIdForUpdate(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", cartId));

        if (!reservation.getStatus().equals(ReservationStatus.PENDING)) {
            throw new IllegalStateException("Cart is not in pending state");
        }

        if (reservation.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        if (reservation.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Cart has expired");
        }

        // TODO: Validate payment method via Feign (if payment service exists)
        // TODO: Reserve inventory (Redis/DB) with outbox pattern to ensure consistency
        // TODO: Publish event via Kafka: reservation.created (with outbox entry)
        // The service will wait for payment.approved event from payment service to confirm

        reservation.setStatus(ReservationStatus.CONFIRMED);
        Reservation updated = reservationRepository.save(reservation);

        log.info("Checkout completed for cart: {}, reservation id: {}", cartId, updated.getId());

        return reservationMapper.toResponse(updated);
    }

    @Override
    public ReservationResponseDto confirm(UUID reservationId) {
        log.info("Confirming reservation: {}", reservationId);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

        if (!reservation.getStatus().equals(ReservationStatus.PENDING)) {
            throw new IllegalStateException("Reservation is not in pending state");
        }

        reservation.setStatus(ReservationStatus.CONFIRMED);
        Reservation updated = reservationRepository.save(reservation);

        // TODO: Publish event via Kafka: reservation.confirmed

        log.info("Reservation confirmed: {}", reservationId);

        return reservationMapper.toResponse(updated);
    }

    @Override
    public ReservationResponseDto cancel(UUID reservationId) {
        log.info("Cancelling reservation: {}", reservationId);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

        if (reservation.getStatus().equals(ReservationStatus.CANCELLED) || reservation.getStatus().equals(ReservationStatus.EXPIRED)) {
            throw new IllegalStateException("Cannot cancel reservation with status: " + reservation.getStatus());
        }

        // Release inventory for all items
        for (ReservationItem item : reservation.getItems()) {
            inventoryService.releaseInventory(
                    item.getSessionId(),
                    item.getSeatId(),
                    item.getQuantity()
            );
        }

        reservation.setStatus(ReservationStatus.CANCELLED);
        Reservation updated = reservationRepository.save(reservation);

        log.info("Reservation cancelled: {}", reservationId);

        return reservationMapper.toResponse(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationResponseDto findById(UUID reservationId) {
        log.info("Finding reservation by id: {}", reservationId);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));

        return reservationMapper.toResponse(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponseDto> listByCustomer(UUID customerId) {
        log.info("Listing reservations for customer: {}", customerId);

        List<Reservation> reservations = reservationRepository.findByCustomerId(customerId);

        return reservations.stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    @Override
    public void expirePendingReservations() {
        log.info("Expiring pending reservations");

        LocalDateTime now = LocalDateTime.now();
        List<Reservation> expiredReservations = reservationRepository
                .findByStatusAndExpiresAtBefore(ReservationStatus.PENDING, now);

        for (Reservation reservation : expiredReservations) {
            log.info("Expiring reservation: {}", reservation.getId());

            // Release inventory for all items
            for (ReservationItem item : reservation.getItems()) {
                inventoryService.releaseInventory(
                        item.getSessionId(),
                        item.getSeatId(),
                        item.getQuantity()
                );
            }

            reservation.setStatus(ReservationStatus.EXPIRED);
            reservationRepository.save(reservation);

            // TODO: Publish event via Kafka: reservation.expired
        }

        log.info("Expired {} reservations", expiredReservations.size());
    }
}







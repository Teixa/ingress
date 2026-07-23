package com.teixaa.reservation.service.impl;

import com.teixaa.reservation.dto.request.AddItemRequestDto;
import com.teixaa.reservation.dto.response.CartResponseDto;
import com.teixaa.reservation.entity.Reservation;
import com.teixaa.reservation.enums.ReservationStatus;
import com.teixaa.reservation.feign.EventsClient;
import com.teixaa.reservation.feign.VenuesClient;
import com.teixaa.reservation.feign.dto.SessionFeignDto;
import com.teixaa.reservation.mapper.ReservationMapper;
import com.teixaa.reservation.repository.ReservationItemRepository;
import com.teixaa.reservation.repository.ReservationRepository;
import com.teixaa.reservation.service.IInventoryService;
import com.teixaa.reservation.service.IReservationItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationItemRepository reservationItemRepository;

    @Mock
    private ReservationMapper reservationMapper;

    @Mock
    private IInventoryService inventoryService;

    @Mock
    private IReservationItemService reservationItemService;

    @Mock
    private EventsClient eventsClient;

    @Mock
    private VenuesClient venuesClient;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private UUID customerId;
    private UUID cartId;
    private UUID eventId;
    private UUID sessionId;

    @BeforeEach
    void setup() {
        customerId = UUID.randomUUID();
        cartId = UUID.randomUUID();
        eventId = UUID.randomUUID();
        sessionId = UUID.randomUUID();

        // Set TTL via reflection for testing
        try {
            var field = ReservationServiceImpl.class.getDeclaredField("reservationTtlMinutes");
            field.setAccessible(true);
            field.set(reservationService, 15);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set reservationTtlMinutes");
        }
    }

    @Test
    void testCreateCart() {
        Reservation savedReservation = Reservation.builder()
                .id(cartId)
                .customerId(customerId)
                .status(ReservationStatus.PENDING)
                .totalAmount(BigDecimal.ZERO)
                .build();

        CartResponseDto expectedDto = CartResponseDto.builder()
                .id(cartId)
                .customerId(customerId)
                .status("PENDING")
                .totalAmount(BigDecimal.ZERO)
                .build();

        when(reservationRepository.save(any(Reservation.class))).thenReturn(savedReservation);
        when(reservationMapper.toCartResponse(savedReservation)).thenReturn(expectedDto);

        CartResponseDto result = reservationService.createCart(customerId);

        assertNotNull(result);
        assertEquals(customerId, result.getCustomerId());
        assertEquals("PENDING", result.getStatus());

        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(reservationMapper, times(1)).toCartResponse(savedReservation);
    }

    @Test
    void testAddItemToCart_Success() {
        Reservation cart = Reservation.builder()
                .id(cartId)
                .customerId(customerId)
                .status(ReservationStatus.PENDING)
                .totalAmount(BigDecimal.ZERO)
                .build();

        SessionFeignDto session = SessionFeignDto.builder()
                .id(sessionId)
                .eventId(eventId)
                .build();

        AddItemRequestDto request = AddItemRequestDto.builder()
                .eventId(eventId)
                .sessionId(sessionId)
                .price(BigDecimal.valueOf(100))
                .quantity(1)
                .build();

        CartResponseDto expectedDto = CartResponseDto.builder()
                .id(cartId)
                .customerId(customerId)
                .status("PENDING")
                .totalAmount(BigDecimal.valueOf(100))
                .build();

        when(reservationRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(eventsClient.fetchSessionDetails(eventId, sessionId)).thenReturn(session);
        when(inventoryService.reserveInventory(sessionId, null, 1)).thenReturn(true);
        when(reservationItemService.save(any())).thenReturn(null);
        when(reservationRepository.save(cart)).thenReturn(cart);
        when(reservationMapper.toCartResponse(cart)).thenReturn(expectedDto);

        CartResponseDto result = reservationService.addItem(cartId, request);

        assertNotNull(result);
        verify(reservationRepository, times(1)).findById(cartId);
        verify(eventsClient, times(1)).fetchSessionDetails(eventId, sessionId);
        verify(inventoryService, times(1)).reserveInventory(sessionId, null, 1);
    }

    @Test
    void testAddItemToCart_InvalidCart() {
        AddItemRequestDto request = AddItemRequestDto.builder()
                .eventId(eventId)
                .sessionId(sessionId)
                .price(BigDecimal.valueOf(100))
                .quantity(1)
                .build();

        when(reservationRepository.findById(cartId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> reservationService.addItem(cartId, request));
    }

    @Test
    void testAddItemToCart_InvalidSession() {
        Reservation cart = Reservation.builder()
                .id(cartId)
                .customerId(customerId)
                .status(ReservationStatus.PENDING)
                .totalAmount(BigDecimal.ZERO)
                .build();

        AddItemRequestDto request = AddItemRequestDto.builder()
                .eventId(eventId)
                .sessionId(sessionId)
                .price(BigDecimal.valueOf(100))
                .quantity(1)
                .build();

        when(reservationRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(eventsClient.fetchSessionDetails(eventId, sessionId)).thenThrow(new RuntimeException("Session not found"));

        assertThrows(IllegalStateException.class, () -> reservationService.addItem(cartId, request));
    }
}


package com.teixaa.reservation.service.impl;

import com.teixaa.reservation.entity.ReservationItem;
import com.teixaa.reservation.exception.ResourceNotFoundException;
import com.teixaa.reservation.repository.ReservationItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ReservationItemServiceImpl implements IReservationItemService {

    //old

    private final ReservationItemRepository reservationItemRepository;

    @Override
    public ReservationItem save(ReservationItem item) {
        log.debug("Saving reservation item: {}", item.getId());
        return reservationItemRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationItem findById(UUID itemId) {
        log.debug("Finding reservation item by id: {}", itemId);
        return reservationItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("ReservationItem", "id", itemId));
    }

    @Override
    public void delete(ReservationItem item) {
        log.debug("Deleting reservation item: {}", item.getId());
        reservationItemRepository.delete(item);
    }

    @Override
    public void deleteById(UUID itemId) {
        log.debug("Deleting reservation item by id: {}", itemId);
        reservationItemRepository.deleteById(itemId);
    }
}


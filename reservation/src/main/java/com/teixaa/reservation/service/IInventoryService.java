package com.teixaa.reservation.service;

import java.util.UUID;

public interface IInventoryService {

    /**
     * Reserve inventory for a seat or general admission
     * @param sessionId the session id
     * @param seatId optional seat id (null for GA)
     * @param quantity quantity to reserve
     * @return true if reservation was successful
     */
    boolean reserveInventory(UUID sessionId, UUID seatId, Integer quantity);

    /**
     * Release inventory for a seat or general admission
     * @param sessionId the session id
     * @param seatId optional seat id (null for GA)
     * @param quantity quantity to release
     */
    void releaseInventory(UUID sessionId, UUID seatId, Integer quantity);

    /**
     * Check available inventory
     * @param sessionId the session id
     * @param seatId optional seat id (null for GA)
     * @return available quantity
     */
    Integer getAvailableInventory(UUID sessionId, UUID seatId);
}


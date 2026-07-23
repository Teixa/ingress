package com.teixaa.reservation.constants;

public final class ReservationConstants {

    private ReservationConstants() {
        // Private constructor to prevent instantiation
    }

    // Success codes and messages
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Cart created successfully";

    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200_CART = "Cart updated successfully";
    public static final String MESSAGE_200_ITEM_REMOVED = "Item removed from cart successfully";
    public static final String MESSAGE_200_CHECKOUT = "Checkout completed successfully";
    public static final String MESSAGE_200_CANCEL = "Reservation cancelled successfully";
    public static final String MESSAGE_200_CONFIRM = "Reservation confirmed successfully";

    public static final String STATUS_202 = "202";
    public static final String MESSAGE_202 = "Checkout accepted, processing payment";

    // Error codes
    public static final String STATUS_400 = "400";
    public static final String MESSAGE_400 = "Bad request";

    public static final String STATUS_404 = "404";
    public static final String MESSAGE_404 = "Resource not found";

    public static final String STATUS_409 = "409";
    public static final String MESSAGE_409_INVALID_STATE = "Invalid reservation state";
    public static final String MESSAGE_409_EMPTY_CART = "Cart is empty";
    public static final String MESSAGE_409_EXPIRED = "Cart has expired";
    public static final String MESSAGE_409_INVENTORY = "Insufficient inventory";

    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "Internal server error";
}


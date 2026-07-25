package com.teixaa.reservation.controller;

import com.teixaa.reservation.dto.response.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Reservation APIs",
        description = "APIs to manage carts and reservations"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public interface IReservationController {

    @Operation(summary = "Create cart for customer", description = "Creates an empty cart for a customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = CartResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(path = "customers/{customerId}/cart")
    ResponseEntity<CartResponseDto> createCart(@PathVariable UUID customerId);

    @Operation(summary = "Add item to cart", description = "Adds an item to an existing cart")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping(path = "cart/{cartId}/items")
    ResponseEntity<CartResponseDto> addItem(@PathVariable UUID cartId, @Valid @RequestBody AddItemRequestDto request);

    @Operation(summary = "Checkout cart", description = "Initiates checkout for a cart")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content(schema = @Schema(implementation = ReservationResponseDto.class)))
    })
    @PostMapping(path = "cart/{cartId}/checkout")
    ResponseEntity<ReservationResponseDto> checkout(@PathVariable UUID cartId, @Valid @RequestBody CheckoutRequestDto request);

    @Operation(summary = "Find reservation by id", description = "Fetch reservation details")
    @GetMapping(path = "reservations/{id}")
    ResponseEntity<ReservationResponseDto> findById(@PathVariable UUID id);

    @Operation(summary = "List reservations by customer", description = "List reservations for a customer")
    @GetMapping(path = "customers/{customerId}/reservations")
    ResponseEntity<List<ReservationResponseDto>> listByCustomer(@PathVariable UUID customerId);

    @Operation(summary = "Cancel reservation", description = "Cancels a reservation (pending/confirmed) and releases inventory)")
    @PostMapping(path = "reservations/{id}/cancel")
    ResponseEntity<ReservationResponseDto> cancel(@PathVariable UUID id);
}


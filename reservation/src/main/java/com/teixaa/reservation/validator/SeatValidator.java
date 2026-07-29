package com.teixaa.reservation.validator;

import com.teixaa.reservation.dto.request.CreateReservationRequestDto;
import com.teixaa.reservation.integration.venue.VenueFeignClient;
import com.teixaa.reservation.mapper.SeatValidationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeatValidator {

    private final VenueFeignClient venueFeignClient;
    private final SeatValidationMapper mapper;

    public void validate(CreateReservationRequestDto request) {

        venueFeignClient.validateSeats(
                mapper.toRequest(request));

    }

}
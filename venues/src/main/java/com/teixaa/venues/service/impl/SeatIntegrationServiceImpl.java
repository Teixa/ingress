package com.teixaa.venues.service.impl;

import com.teixaa.venues.entity.Seat;
import com.teixaa.venues.entity.Sector;
import com.teixaa.venues.enums.OccupancyType;
import com.teixaa.venues.exception.BusinessException;
import com.teixaa.venues.exception.ResourceNotFoundException;
import com.teixaa.venues.integration.dto.SeatValidationItemDto;
import com.teixaa.venues.integration.dto.ValidateSeatsRequest;
import com.teixaa.venues.repository.SeatRepository;
import com.teixaa.venues.repository.SectorRepository;
import com.teixaa.venues.service.ISeatIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeatIntegrationServiceImpl implements ISeatIntegrationService {

    private final SectorRepository sectorRepository;
    private final SeatRepository seatRepository;

    @Override
    public void validateSeats(ValidateSeatsRequest request) {

        for (SeatValidationItemDto item : request.getItems()) {

            Sector sector = sectorRepository.findById(item.getSectorId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Sector",
                            "id",
                            item.getSectorId().toString()));

            /*
             * Setor de lugares marcados
             */
            if (sector.getOccupancyType() == OccupancyType.RESERVED_SEATING) {

                if (item.getSeatId() == null) {
                    throw new BusinessException(
                            "Seat is required for RESERVED sectors.");
                }

                Seat seat = seatRepository.findById(item.getSeatId())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Seat",
                                "id",
                                item.getSeatId().toString()));

                if (!seat.getSector().getId().equals(sector.getId())) {
                    throw new BusinessException(
                            "Seat does not belong to the informed sector.");
                }
            }

            /*
             * Setor geral
             */
            if (sector.getOccupancyType() == OccupancyType.GENERAL_ADMISSION
                    && item.getSeatId() != null) {

                throw new BusinessException(
                        "General admission sectors cannot receive seatId.");
            }
        }
    }
}

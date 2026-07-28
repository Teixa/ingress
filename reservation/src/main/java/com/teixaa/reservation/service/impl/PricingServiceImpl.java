package com.teixaa.reservation.service.impl;

import com.teixaa.reservation.entity.Reservation;
import com.teixaa.reservation.entity.ReservationItem;
import com.teixaa.reservation.exception.BusinessException;
import com.teixaa.reservation.integration.event.client.EventClient;
import com.teixaa.reservation.integration.event.dto.SectorPriceIntegrationResponse;
import com.teixaa.reservation.integration.event.dto.SessionPriceIntegrationResponse;
import com.teixaa.reservation.service.IPricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements IPricingService {

    private final EventClient eventClient;

    @Override
    public void calculate(Reservation reservation) {

        SessionPriceIntegrationResponse response =
                eventClient.getPrices(
                        reservation.getSessionId());

        Map<UUID, BigDecimal> prices =
                response.getPrices()
                        .stream()
                        .collect(Collectors.toMap(
                                SectorPriceIntegrationResponse::getSectorId,
                                SectorPriceIntegrationResponse::getUnitPrice
                        ));

        BigDecimal total = BigDecimal.ZERO;

        for (ReservationItem item : reservation.getItems()) {

            BigDecimal unitPrice =
                    prices.get(item.getSectorId());

            if (unitPrice == null) {
                throw new BusinessException(
                        "Sector without configured price.");
            }

            item.updateUnitPrice(unitPrice);

            total = total.add(item.getSubtotal());

        }

        reservation.updateTotalAmount(total);

    }

}
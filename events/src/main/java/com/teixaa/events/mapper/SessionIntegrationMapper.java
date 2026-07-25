package com.teixaa.events.mapper;

import com.teixaa.events.entity.Session;
import com.teixaa.events.entity.SessionPrice;
import com.teixaa.events.integration.dto.SectorPriceIntegrationResponse;
import com.teixaa.events.integration.dto.SessionIntegrationResponse;
import com.teixaa.events.integration.dto.SessionPriceIntegrationResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SessionIntegrationMapper {

    SessionIntegrationResponse toIntegration(Session session);

    SectorPriceIntegrationResponse toIntegration(SessionPrice price);

    List<SectorPriceIntegrationResponse> toIntegration(
            List<SessionPrice> prices);

    default SessionPriceIntegrationResponse toIntegration(
            UUID sessionId,
            List<SessionPrice> prices) {

        return SessionPriceIntegrationResponse.builder()
                .sessionId(sessionId)
                .prices(toIntegration(prices))
                .build();
    }

}
package com.teixaa.events.mapper;

import com.teixaa.events.entity.Session;
import com.teixaa.events.entity.SessionPrice;
import com.teixaa.events.integration.dto.SectorPriceIntegrationResponse;
import com.teixaa.events.integration.dto.SessionIntegrationResponse;
import com.teixaa.events.integration.dto.SessionPriceIntegrationResponse;
import com.teixaa.events.integration.dto.SessionSectorPriceIntegrationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SessionIntegrationMapper {

    SessionIntegrationResponse toIntegration(Session session);

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

    @Mapping(target = "sessionId", source = "session.id")
    @Mapping(target = "sectorId", source = "sectorId")
    @Mapping(target = "unitPrice", source = "unitPrice")
    SessionSectorPriceIntegrationResponse toIntegration(
            SessionPrice sessionPrice);

}
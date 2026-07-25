package com.teixaa.reservation.validator;

import com.teixaa.reservation.exception.BusinessException;
import com.teixaa.reservation.integration.event.client.EventClient;
import com.teixaa.reservation.integration.event.dto.SessionIntegrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionValidator {

    private final EventClient eventClient;

    public SessionIntegrationResponse validate(UUID sessionId) {

        SessionIntegrationResponse session =
                eventClient.getSession(sessionId);

        if (session == null) {
            throw new BusinessException("Session not found.");
        }

        if (LocalDateTime.now().isBefore(session.getSalesStart())) {
            throw new BusinessException(
                    "Ticket sales have not started yet.");
        }

        if (LocalDateTime.now().isAfter(session.getSalesEnd())) {
            throw new BusinessException(
                    "Ticket sales have ended.");
        }

        return session;
    }

}
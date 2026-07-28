package com.teixaa.reservation.scheduler;

import com.teixaa.reservation.service.IReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ReservationExpirationScheduler {

    //old
//
//    private final IReservationService reservationService;
//
//    /**
//     * Runs every 10 minutes to check and expire pending reservations that have passed their TTL.
//     * Can be configured via property: reservation.expiration.check-interval-ms
//     *
//     * @SchedulerLock ensures this task only runs on one instance in a distributed cluster.
//     */
//    @Scheduled(fixedDelayString = "${reservation.expiration.check-interval-ms:600000}")
//    @SchedulerLock(name = "expireOldReservations",
//                   lockAtMostFor = "9m",
//                   lockAtLeastFor = "30s")
//    public void expireOldReservations() {
//        log.debug("Starting reservation expiration check");
//        try {
//            reservationService.expirePendingReservations();
//        } catch (Exception e) {
//            log.error("Error during reservation expiration check", e);
//        }
//    }
}



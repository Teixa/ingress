package com.teixaa.reservation.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "15m") // Formato simplificado string ou use Duration
public class SchedulingConfiguration {

    @Bean
    public LockProvider lockProvider(JdbcTemplate jdbcTemplate) {
        // Nas versões 5.x do ShedLock, injetar diretamente o JdbcTemplate é o padrão recomendado
        return new JdbcTemplateLockProvider(
                JdbcTemplateLockProvider.Configuration.builder()
                        .withJdbcTemplate(jdbcTemplate)
                        .usingDbTime() // Garante o uso do relógio do sgbd
                        .build()
        );
    }
}

package com.teixaa.events.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSessionRequest {

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private LocalDateTime salesStart;

    @NotNull
    private LocalDateTime salesEnd;

    @NotNull
    private Duration duration;

    @NotNull
    private LocalDateTime gatesOpenAt;

    @Size(max = 500)
    private String notes;

}

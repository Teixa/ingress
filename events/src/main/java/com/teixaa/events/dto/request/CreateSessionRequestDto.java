package com.teixaa.events.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSessionRequestDto {

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

    @NotEmpty
    @Nullable
    private List<CreateSessionPriceRequestDto> prices;

}

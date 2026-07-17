package com.teixaa.events.dto.response;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyOrganizerResponseDto {
    private UUID id;

    private String companyName;

    private String cnpj;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private String createdBy;
}

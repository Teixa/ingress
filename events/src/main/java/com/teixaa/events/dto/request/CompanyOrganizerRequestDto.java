package com.teixaa.events.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyOrganizerRequestDto {

    @NotBlank(message = "Company name is required.")
    @Size(max = 255)
    private String companyName;

    @NotBlank(message = "CNPJ is required.")
    @Pattern(
            regexp = "\\d{14}",
            message = "CNPJ must contain exactly 14 digits."
    )
    private String cnpj;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email.")
    @Size(max = 255)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Size(max = 20)
    private String phoneNumber;
}

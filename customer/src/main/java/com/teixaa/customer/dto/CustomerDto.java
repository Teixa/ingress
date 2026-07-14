package com.teixaa.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer information"
)
public class CustomerDto {

    @Schema(
            description = "Name of the customer", example = "John Doe"
    )
    @NotEmpty(message = "name can not be a null or empty")
    @Size(min = 5, max = 30, message = "the length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "teixaa@email.com"
    )
    @NotEmpty(message = "email address can not be a null or empty")
    @Email(message = "should be a valid email")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "93454321231"
    )
    @NotEmpty(message = "number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits, only numbers (XX) XXXXXX-XXXX")
    private String mobileNumber;
}

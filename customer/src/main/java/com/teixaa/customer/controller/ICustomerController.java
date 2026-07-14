package com.teixaa.customer.controller;


import com.teixaa.customer.dto.CustomerDto;
import com.teixaa.customer.dto.ErrorResponseDto;
import com.teixaa.customer.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(
        name = "CRUD REST APIs for Customers in Ingress",
        description = "CRUD REST APIs in Ingress to CREATE, UPDATE, FETCH AND DELETE customer details"
)
@RestController()
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ICustomerController {


    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a email"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = {@Content (mediaType = "application/json",schema = @Schema(implementation =  CustomerDto.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping(path = "fetchCustomerDetails")
    ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam("email")
                                                     @Pattern(
                                                             regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                                                             message = "The provided email address format is invalid"
                                                     )
                                                     String email
    );

    @Operation(
            summary = "Create Customer REST API",
            description = "REST API to create Customer based on a email"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED",
                    content = {@Content (mediaType = "application/json",schema = @Schema(implementation =  CustomerDto.class))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping(path = "createCustomerDetails")
    public ResponseEntity<ResponseDto> createCustomer (@Valid @RequestBody CustomerDto customerDto);
}

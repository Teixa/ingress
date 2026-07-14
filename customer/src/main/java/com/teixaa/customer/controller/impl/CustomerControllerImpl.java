package com.teixaa.customer.controller.impl;

import com.teixaa.customer.controller.ICustomerController;
import com.teixaa.customer.dto.CustomerDto;
import com.teixaa.customer.dto.ResponseDto;
import com.teixaa.customer.service.ICustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerControllerImpl implements ICustomerController {

    ICustomerService ICustomerService;

    public CustomerControllerImpl(ICustomerService ICustomerService) {
        this.ICustomerService = ICustomerService;
    }



//    @GetMapping(path = "fetchCustomerDetails")
//    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam
//                                                                @Pattern(
//                                                                        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
//                                                                        message = "The provided email address format is invalid"
//                                                                )
//                                                                String email) {
//        return ResponseEntity.status(HttpStatus.OK).body(ICustomerService.fetchCustomerDetails(email));
//    }

    @Override
    public ResponseEntity<CustomerDto> fetchCustomerDetails(String email) {
        return ResponseEntity.status(HttpStatus.OK).body(ICustomerService.fetchCustomerDetails(email));
    }

    @Override
    public ResponseEntity<ResponseDto> createCustomer (CustomerDto customerDto) {
        ICustomerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("201", "Customer Created"));
    }


}


//Responsabilidades
//Cadastro
//Login
//Perfil
//Endereço
//Telefones
//Preferências

//eventos
// UsuarioCriado
//
//UsuarioAtualizado
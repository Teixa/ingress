package com.teixaa.customer.controller;

import com.teixaa.customer.dto.CustomerDto;
import com.teixaa.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "fetchCustomerDetails")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.fetchCustomerDetails(email));
    }

    @PostMapping(path = "createCustomerDetails")
    public ResponseEntity<CustomerDto> createCustomer (@RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.createCustomer(customerDto));
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
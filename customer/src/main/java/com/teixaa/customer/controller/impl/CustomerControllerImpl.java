package com.teixaa.customer.controller.impl;

import com.teixaa.customer.controller.ICustomerController;
import com.teixaa.customer.dto.CustomerDto;
import com.teixaa.customer.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerControllerImpl implements ICustomerController {

    ICustomerService ICustomerService;

    public CustomerControllerImpl(ICustomerService ICustomerService) {
        this.ICustomerService = ICustomerService;
    }

    @GetMapping(path = "fetchCustomerDetails")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(ICustomerService.fetchCustomerDetails(email));
    }

    @PostMapping(path = "createCustomerDetails")
    public ResponseEntity<CustomerDto> createCustomer (@RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ICustomerService.createCustomer(customerDto));
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
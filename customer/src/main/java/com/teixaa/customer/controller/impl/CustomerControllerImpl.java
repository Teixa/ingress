package com.teixaa.customer.controller.impl;

import com.teixaa.customer.constants.CustomerConstants;
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



    @Override
    public ResponseEntity<CustomerDto> fetchCustomerDetails(String email) {
        return ResponseEntity.status(HttpStatus.OK).body(ICustomerService.fetchCustomerDetails(email));
    }

    @Override
    public ResponseEntity<ResponseDto> createCustomer (CustomerDto customerDto) {
        ICustomerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_201));
    }

    @Override
    public ResponseEntity<ResponseDto> updateCustomer(CustomerDto customerDto) {
        boolean isUpdated = ICustomerService.updateCustomer(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CustomerConstants.STATUS_200, CustomerConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CustomerConstants.STATUS_417, CustomerConstants.MESSAGE_417_UPDATE));
        }
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
package com.teixaa.events.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company_organizers")
@SuperBuilder
public class CompanyOrganizer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String companyName;

    @Column(unique = true, nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String email;

    private String phoneNumber;

}

//organizador
//--------------------------
//id
//nome
//cpf/cnpj
//email
//telefone

//1Organizador x N Eventos
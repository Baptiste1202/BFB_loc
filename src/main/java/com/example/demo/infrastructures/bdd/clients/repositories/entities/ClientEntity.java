package com.example.demo.infrastructures.bdd.clients.repositories.entities;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection= "clients")
public class ClientEntity {

    @Id 
    private UUID identifier;
    private String lastname;
    private String firstname;
    private Date dateOfBirth;
    private String numPermis;
    private String address;
    private Collection<VehicleEntity> vehicles;
}


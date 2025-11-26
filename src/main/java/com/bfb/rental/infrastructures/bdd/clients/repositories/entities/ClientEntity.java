package com.bfb.rental.infrastructures.bdd.clients.repositories.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection= "clients")
public class ClientEntity {

    @Id private String identifier;
    private String lastname;
    private String firstname;
    private Date date_of_birth;
    private String num_permis;
    private String address;
    private Collection<VehicleEntity> vehicles;
}


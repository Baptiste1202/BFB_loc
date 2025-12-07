package com.bfb.rental.infrastructures.bdd.clients.repositories.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
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
@CompoundIndex(name = "unique_client", def = "{'lastname': 1, 'firstname': 1, 'date_of_birth': 1}", unique = true)
public class ClientEntity {

    @Id private String identifier;
    private String lastname;
    private String firstname;
    private String date_of_birth;
    @Indexed(unique = true)
    private String num_permis;
    private String address;
    private Collection<VehicleEntity> vehicles;
    private Date dateCreation;
    private Date dateModification;
}


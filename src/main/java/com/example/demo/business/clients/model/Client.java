package com.example.demo.business.clients.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import com.example.demo.business.vehicles.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Client {

    private UUID identifier;

    /**
     * Nom de famille du client
     */
    private String lastname;

    /**
     * Prénom du client
     */
    private String firstname;

    /**
     * Date de naissance du client
     */
    private Date dateOfBirth;

    /**
     * Date de naissance du client
     */
    private String numPermis;

    /**
     * Adresse du client
     */
    private String address;

    /**
     * Collection des véhicules associés au client
     */
    @Builder.Default
    private Collection<Vehicle> vehicles = Collections.emptySet();
    
}

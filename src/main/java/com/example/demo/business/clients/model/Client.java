package com.example.demo.business.clients.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import com.example.demo.business.vehicles.model.Vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Client {

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
    private Date date_of_birth;

    /**
     * Date de naissance du client
     */
    private String num_permis;

    /**
     * Adresse du client
     */
    private String address;

    /**
     * Collection des véhicules associés au client
     */
    private final Collection<Vehicle> vehicles = Collections.emptySet();
}

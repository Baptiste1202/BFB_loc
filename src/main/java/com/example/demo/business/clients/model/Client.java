package com.example.demo.business.clients.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

import com.example.demo.business.vehicles.model.Vehicle;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Client {

    @NotNull(message = "L'identifiant ne peut pas être nul")
    private final UUID identifier;

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
    @Builder.Default
    private final Collection<Vehicle> vehicles = Collections.emptySet();
    
}

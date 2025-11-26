package com.bfb.rental.business.vehicles.model;

import java.util.Date;
import java.util.UUID;

import com.bfb.rental.business.common.EtatVehicule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Vehicle {

    private final UUID identifier;
    
    /**
     * Marque du véhicule
     */
    private String brand;

    /**
     * Modèle du véhicule
     */
    private String model;

    /**
     * Motorisation du véhicule
     */
    private String motorisation;

    /**
     * Couleur du véhicule
     */
    private String color;

    /**
     * Numéro d'immatriculation du véhicule
     */
    private String immatriculation;

    /**
     * Date d'acquisition du véhicule
     */
    private Date acquisition_date;

    /**
     * Etat du véhicule
     */
    private EtatVehicule etat;
}

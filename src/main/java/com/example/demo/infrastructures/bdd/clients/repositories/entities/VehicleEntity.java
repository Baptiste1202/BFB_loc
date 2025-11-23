package com.example.demo.infrastructures.bdd.clients.repositories.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {

    private String identifier;
    private String brand;
    private String model;
    private String motorisation;
    private String color;
    private String immatriculation;
    private Date acquisition_date;
    private String etat;
}


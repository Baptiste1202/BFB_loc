package com.example.demo.infrastructures.bdd.clients.repositories.entities;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.business.common.VehicleStateEnum;

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
@Document(collection= "vehicles")
public class VehicleEntity {

    private UUID identifier;
    private String brand;
    private String model;
    private String motorisation;
    private String color;
    private String immatriculation;
    private Date acquisitionDate;
    private VehicleStateEnum etat;
}


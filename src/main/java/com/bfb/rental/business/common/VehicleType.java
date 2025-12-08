package com.bfb.rental.business.common;

public enum VehicleType {
    VOITURE("Voiture"),
    CAMION("Camion");

    private final String label;

    VehicleType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
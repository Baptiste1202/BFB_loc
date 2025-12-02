package com.bfb.rental.interfaces.exceptions;

import java.time.LocalDate;

/**
 * JUSTIFICATION : Exception métier très spécifique
 *
 * Lancée quand on essaie de louer un véhicule en conflit de périodes.
 * Le client a besoin de savoir c'est un CONFLIT (409 Conflict) pas une erreur (500).
 */
public class ConflitLocationException extends BusinessException {

    private final String vehiculeId;
    private final LocalDate dateDebut;
    private final LocalDate dateFin;

    public ConflitLocationException(String vehiculeId, LocalDate dateDebut, LocalDate dateFin) {
        super(String.format(
                "Véhicule %s n'est pas disponible du %s au %s",
                vehiculeId, dateDebut, dateFin
        ));
        this.vehiculeId = vehiculeId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.httpStatusCode = 409; // Conflict
        this.errorCode = "LOCATION_CONFLICT";
    }

    public String getVehiculeId() {
        return vehiculeId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }
}
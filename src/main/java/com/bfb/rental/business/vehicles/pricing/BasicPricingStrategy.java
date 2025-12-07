package com.bfb.rental.business.vehicles.pricing;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicPricingStrategy implements PricingStrategy {

    @Override
    public BigDecimal calculatePrice(Contrat contrat, TransportVehicle vehicule) {
        long nombreJours = ChronoUnit.DAYS.between(contrat.getDateDebut(), contrat.getDateFin());

        if (nombreJours == 0) {
            nombreJours = 1;
        }

        BigDecimal prixJournalier = vehicule.getPrixLocationJournalier();
        BigDecimal prixTotal = prixJournalier.multiply(BigDecimal.valueOf(nombreJours));

        log.info("Stratégie BASIQUE - Contrat: {} - Véhicule: {} - Jours: {} - Prix/jour: {} - Total: {}",
                contrat.getId(), vehicule.getImmatriculation(), nombreJours, prixJournalier, prixTotal);

        return prixTotal;
    }

    @Override
    public String getStrategyName() {
        return "BASIQUE";
    }
}
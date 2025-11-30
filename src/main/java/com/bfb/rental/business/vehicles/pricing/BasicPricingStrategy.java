package com.bfb.rental.business.vehicles.pricing;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;

import lombok.extern.slf4j.Slf4j;

/**
 * Stratégie de prix basique : multiplication du prix journalier par le nombre de jours
 */
@Slf4j
public class BasicPricingStrategy implements PricingStrategy {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BasicPricingStrategy.class);

    @Override
    public BigDecimal calculatePrice(Contrat contrat) {
        long nombreJours = ChronoUnit.DAYS.between(contrat.getDateDebut(), contrat.getDateFin());
        
        // Si c'est le même jour, c'est 1 jour de location
        if (nombreJours == 0) {
            nombreJours = 1;
        }

        BigDecimal prixJournalier = contrat.getVehicule().getPrixLocationJournalier();
        BigDecimal prixTotal = prixJournalier.multiply(BigDecimal.valueOf(nombreJours));

        log.info("Stratégie BASIQUE - Contrat: {} - Véhicule: {} - Jours: {} - Prix/jour: {} - Total: {}",
                contrat.getId(), contrat.getVehicule().getImmatriculation(), nombreJours, prixJournalier, prixTotal);

        return prixTotal;
    }

    @Override
    public String getStrategyName() {
        return "BASIQUE";
    }
}

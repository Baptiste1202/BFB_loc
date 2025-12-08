package com.bfb.rental.business.vehicles.pricing;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeeklyDiscountPricingStrategy implements PricingStrategy {

    private static final long DAYS_FOR_DISCOUNT = 7;
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10");

    @Override
    public BigDecimal calculatePrice(Contrat contrat) {
        long nombreJours = ChronoUnit.DAYS.between(contrat.getDateDebut(), contrat.getDateFin());

        if (nombreJours == 0) {
            nombreJours = 1;
        }

        TransportVehicle vehicule = contrat.getVehicule();
        BigDecimal prixJournalier = vehicule.getPrixLocationJournalier();
        BigDecimal prixTotal = prixJournalier.multiply(BigDecimal.valueOf(nombreJours));

        if (nombreJours >= DAYS_FOR_DISCOUNT) {
            BigDecimal reduction = prixTotal.multiply(DISCOUNT_RATE);
            prixTotal = prixTotal.subtract(reduction);
            log.info("Stratégie RÉDUCTION HEBDO - Contrat: {} - Véhicule: {} - Jours: {} - Réduction: {} - Total: {}",
                    contrat.getId(), vehicule.getImmatriculation(), nombreJours, reduction, prixTotal);
        } else {
            log.info("Stratégie RÉDUCTION HEBDO - Contrat: {} - Véhicule: {} - Jours: {} - Pas de réduction - Total: {}",
                    contrat.getId(), vehicule.getImmatriculation(), nombreJours, prixTotal);
        }

        return prixTotal;
    }

    @Override
    public String getStrategyName() {
        return "RÉDUCTION_HEBDOMADAIRE";
    }
}
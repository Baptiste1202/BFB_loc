package com.bfb.rental.business.vehicles.pricing;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;

import lombok.extern.slf4j.Slf4j;

/**
 * Stratégie de prix avec réduction hebdomadaire : 10% de réduction à partir de 7 jours
 */
@Slf4j
public class WeeklyDiscountPricingStrategy implements PricingStrategy {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WeeklyDiscountPricingStrategy.class);

    private static final long DAYS_FOR_DISCOUNT = 7;
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10"); // 10% de réduction

    @Override
    public BigDecimal calculatePrice(Contrat contrat) {
        long nombreJours = ChronoUnit.DAYS.between(contrat.getDateDebut(), contrat.getDateFin());
        
        // Si c'est le même jour, c'est 1 jour de location
        if (nombreJours == 0) {
            nombreJours = 1;
        }

        BigDecimal prixJournalier = contrat.getVehicule().getPrixLocationJournalier();
        BigDecimal prixTotal = prixJournalier.multiply(BigDecimal.valueOf(nombreJours));

        // Appliquer la réduction si location >= 7 jours
        if (nombreJours >= DAYS_FOR_DISCOUNT) {
            BigDecimal reduction = prixTotal.multiply(DISCOUNT_RATE);
            prixTotal = prixTotal.subtract(reduction);
            log.info("Stratégie RÉDUCTION HEBDO - Contrat: {} - Véhicule: {} - Jours: {} - Réduction appliquée: {} - Total: {}",
                    contrat.getId(), contrat.getVehicule().getImmatriculation(), nombreJours, reduction, prixTotal);
        } else {
            log.info("Stratégie RÉDUCTION HEBDO - Contrat: {} - Véhicule: {} - Jours: {} - Pas de réduction - Total: {}",
                    contrat.getId(), contrat.getVehicule().getImmatriculation(), nombreJours, prixTotal);
        }

        return prixTotal;
    }

    @Override
    public String getStrategyName() {
        return "RÉDUCTION_HEBDOMADAIRE";
    }
}

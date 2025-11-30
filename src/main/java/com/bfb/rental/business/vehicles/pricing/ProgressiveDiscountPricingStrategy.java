package com.bfb.rental.business.vehicles.pricing;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;

import lombok.extern.slf4j.Slf4j;

/**
 * Stratégie de prix avec réduction progressive :
 * - 0-3 jours : prix normal
 * - 4-6 jours : 5% de réduction
 * - 7-14 jours : 10% de réduction
 * - 15+ jours : 20% de réduction
 */
@Slf4j
public class ProgressiveDiscountPricingStrategy implements PricingStrategy {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProgressiveDiscountPricingStrategy.class);

    @Override
    public BigDecimal calculatePrice(Contrat contrat) {
        long nombreJours = ChronoUnit.DAYS.between(contrat.getDateDebut(), contrat.getDateFin());
        
        // Si c'est le même jour, c'est 1 jour de location
        if (nombreJours == 0) {
            nombreJours = 1;
        }

        BigDecimal prixJournalier = contrat.getVehicule().getPrixLocationJournalier();
        BigDecimal prixTotal = prixJournalier.multiply(BigDecimal.valueOf(nombreJours));
        
        BigDecimal discountRate;
        String discountDescription;

        if (nombreJours <= 3) {
            discountRate = BigDecimal.ZERO;
            discountDescription = "Pas de réduction";
        } else if (nombreJours <= 6) {
            discountRate = new BigDecimal("0.05"); // 5%
            discountDescription = "5% de réduction (4-6 jours)";
        } else if (nombreJours <= 14) {
            discountRate = new BigDecimal("0.10"); // 10%
            discountDescription = "10% de réduction (7-14 jours)";
        } else {
            discountRate = new BigDecimal("0.20"); // 20%
            discountDescription = "20% de réduction (15+ jours)";
        }

        if (discountRate.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal reduction = prixTotal.multiply(discountRate);
            prixTotal = prixTotal.subtract(reduction);
        }

        log.info("Stratégie RÉDUCTION PROGRESSIVE - Contrat: {} - Véhicule: {} - Jours: {} - {} - Total: {}",
                contrat.getId(), contrat.getVehicule().getImmatriculation(), nombreJours, discountDescription, prixTotal);

        return prixTotal;
    }

    @Override
    public String getStrategyName() {
        return "RÉDUCTION_PROGRESSIVE";
    }
}

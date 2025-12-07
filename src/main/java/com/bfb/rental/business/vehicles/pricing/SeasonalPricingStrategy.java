package com.bfb.rental.business.vehicles.pricing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SeasonalPricingStrategy implements PricingStrategy {

    private static final BigDecimal HIGH_SEASON_MULTIPLIER = new BigDecimal("1.30");
    private static final BigDecimal NORMAL_MULTIPLIER = new BigDecimal("1.0");

    @Override
    public BigDecimal calculatePrice(Contrat contrat, TransportVehicle vehicule) {
        long nombreJours = ChronoUnit.DAYS.between(contrat.getDateDebut(), contrat.getDateFin());

        if (nombreJours == 0) {
            nombreJours = 1;
        }

        BigDecimal prixJournalier = vehicule.getPrixLocationJournalier();
        BigDecimal prixTotal = BigDecimal.ZERO;

        LocalDate currentDate = contrat.getDateDebut();
        while (!currentDate.isAfter(contrat.getDateFin())) {
            Month mois = currentDate.getMonth();
            BigDecimal multiplicateur = (mois == Month.JULY || mois == Month.AUGUST)
                    ? HIGH_SEASON_MULTIPLIER
                    : NORMAL_MULTIPLIER;

            prixTotal = prixTotal.add(prixJournalier.multiply(multiplicateur));
            currentDate = currentDate.plusDays(1);
        }

        log.info("Stratégie SAISONNIÈRE - Contrat: {} - Véhicule: {} - Jours: {} - Total: {}",
                contrat.getId(), vehicule.getImmatriculation(), nombreJours, prixTotal);

        return prixTotal;
    }

    @Override
    public String getStrategyName() {
        return "SAISONNIÈRE";
    }
}
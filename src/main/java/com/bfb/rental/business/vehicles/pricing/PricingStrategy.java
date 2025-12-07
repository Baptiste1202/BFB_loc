package com.bfb.rental.business.vehicles.pricing;

import java.math.BigDecimal;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;

/**
 * Interface Strategy pour les stratégies de calcul de prix de location
 */
public interface PricingStrategy {
    /**
     * Calcule le prix total de location basé sur la stratégie
     *
     * @param contrat le contrat de location
     * @return le prix total calculé
     */
    BigDecimal calculatePrice(Contrat contrat, TransportVehicle vehicule);

    String getStrategyName();
}

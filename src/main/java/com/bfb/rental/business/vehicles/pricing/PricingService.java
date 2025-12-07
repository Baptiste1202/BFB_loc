package com.bfb.rental.business.vehicles.pricing;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PricingService {

    private final PricingStrategyFactory strategyFactory;

    /**
     * Calcule le prix avec une stratégie spécifique
     */
    public BigDecimal calculatePrice(Contrat contrat, TransportVehicle vehicule, String strategyName) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Véhicule obligatoire");
        }
        PricingStrategy strategy = strategyFactory.createStrategy(strategyName);
        return strategy.calculatePrice(contrat, vehicule);
    }

    /**
     * Calcule le prix avec la stratégie par défaut
     */
    public BigDecimal calculateDefaultPrice(Contrat contrat, TransportVehicle vehicule) {
        if (vehicule == null) {
            throw new IllegalArgumentException("Véhicule obligatoire");
        }
        return strategyFactory.getDefaultStrategy().calculatePrice(contrat, vehicule);
    }
}
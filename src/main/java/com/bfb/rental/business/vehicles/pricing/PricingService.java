package com.bfb.rental.business.vehicles.pricing;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;

import lombok.extern.slf4j.Slf4j;

/**
 * Service pour le calcul des prix de location avec les différentes stratégies
 */
@Service
@Slf4j
public class PricingService {

    private final PricingStrategyFactory strategyFactory;

    public PricingService(PricingStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    /**
     * Calcule le prix de location d'un contrat avec une stratégie spécifique
     *
     * @param contrat       le contrat de location
     * @param strategyName  le nom de la stratégie à utiliser
     * @return le prix total calculé
     */
    public BigDecimal calculateContractPrice(Contrat contrat, String strategyName) {
        if (contrat.getVehicule() == null) {
            throw new IllegalArgumentException("Véhicule non présent dans le contrat");
        }
        PricingStrategy strategy = strategyFactory.createStrategy(strategyName);
        return calculateContractPrice(contrat, strategy);
    }

    /**
     * Calcule le prix de location d'un contrat avec une stratégie fournie
     *
     * @param contrat   le contrat de location
     * @param vehicle   le véhicule à louer
     * @param strategy  la stratégie à utiliser
     * @return le prix total calculé
     */
    public BigDecimal calculateContractPrice(Contrat contrat, PricingStrategy strategy) {
        return strategy.calculatePrice(contrat);
    }

    /**
     * Calcule le prix de location d'un contrat avec la stratégie par défaut
     *
     * @param contrat le contrat de location
     * @return le prix total calculé
     */
    public BigDecimal calculateDefaultContractPrice(Contrat contrat) {
        if (contrat.getVehicule() == null) {
            throw new IllegalArgumentException("Véhicule non présent dans le contrat");
        }
        return calculateContractPrice(contrat, strategyFactory.getDefaultStrategy());
    }
}

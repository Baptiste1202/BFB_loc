package com.bfb.rental.business.vehicles.pricing;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Factory pour créer les différentes stratégies de prix
 */
@Slf4j
@Component
public class PricingStrategyFactory {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(PricingStrategyFactory.class);

    private static final Map<String, Class<? extends PricingStrategy>> STRATEGIES = new HashMap<>();

    static {
        STRATEGIES.put("BASIQUE", BasicPricingStrategy.class);
        STRATEGIES.put("RÉDUCTION_HEBDOMADAIRE", WeeklyDiscountPricingStrategy.class);
        STRATEGIES.put("SAISONNIÈRE", SeasonalPricingStrategy.class);
        STRATEGIES.put("RÉDUCTION_PROGRESSIVE", ProgressiveDiscountPricingStrategy.class);
    }

    /**
     * Crée une instance de la stratégie demandée
     *
     * @param strategyName le nom de la stratégie
     * @return une instance de la stratégie
     * @throws IllegalArgumentException si la stratégie n'existe pas
     */
    public PricingStrategy createStrategy(String strategyName) {
        Class<? extends PricingStrategy> strategyClass = STRATEGIES.get(strategyName.toUpperCase());
        
        if (strategyClass == null) {
            log.warn("Stratégie inconnue: {}. Utilisation de la stratégie BASIQUE par défaut.", strategyName);
            strategyClass = BasicPricingStrategy.class;
        }

        try {
            return strategyClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("Erreur lors de la création de la stratégie: {}", strategyName, e);
            return new BasicPricingStrategy();
        }
    }

    /**
     * Retourne une stratégie par défaut
     *
     * @return une instance de BasicPricingStrategy
     */
    public PricingStrategy getDefaultStrategy() {
        return new BasicPricingStrategy();
    }

    /**
     * Retourne les stratégies disponibles
     *
     * @return les noms des stratégies disponibles
     */
    public java.util.Set<String> getAvailableStrategies() {
        return STRATEGIES.keySet();
    }
}

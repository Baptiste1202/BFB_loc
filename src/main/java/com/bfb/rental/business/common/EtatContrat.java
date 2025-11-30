package com.bfb.rental.business.common;

/**
 * Énumération des états possibles d'un contrat de location
 */
public enum EtatContrat {
    /**
     * Contrat créé mais pas encore confirmé
     */
    CREATED,

    /**
     * Contrat confirmé et actif
     */
    ACTIVE,

    /**
     * Contrat complété avec retour du véhicule
     */
    COMPLETED,

    /**
     * Contrat annulé
     */
    CANCELLED,

    /**
     * Contrat suspendu
     */
    SUSPENDED
}

package com.bfb.rental.validateur;

import java.util.List;

import org.springframework.lang.Contract;

public class ContratValidationService {

    private final Validator<Contract> validatorChain;

    public ContratValidationService(List<Validator<Contract>> validators) {

        // On les récupère dans l'ordre défini par @Order
        for (int i = 0; i < validators.size() - 1; i++) {
            validators.get(i).setNext(validators.get(i + 1));
        }

        this.validatorChain = validators.isEmpty() ? null : validators.get(0);
    }

    public void validate(Contract contract) {
        if (validatorChain != null) {
            validatorChain.validate(contract);
        }
    }
    
}

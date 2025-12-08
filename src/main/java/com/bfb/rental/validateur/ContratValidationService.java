package com.bfb.rental.validateur;

import java.util.List;

import com.bfb.rental.business.contrats.model.Contrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Contract;
import org.springframework.stereotype.Service;

@Service
public class ContratValidationService {

    private final Validator<Contrat> validatorChain;

    @Autowired
    public ContratValidationService(List<Validator<Contrat>> validators) {

        // On les récupère dans l'ordre défini par @Order
        for (int i = 0; i < validators.size() - 1; i++) {
            validators.get(i).setNext(validators.get(i + 1));
        }

        this.validatorChain = validators.isEmpty() ? null : validators.get(0);
    }

    public void validate(Contrat contract) {
        if (validatorChain != null) {
            validatorChain.validate(contract);
        }
    }
    
}

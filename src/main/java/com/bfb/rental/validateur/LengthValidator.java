package com.bfb.rental.validateur;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bfb.rental.business.contrats.model.Contrat;

import jakarta.validation.ValidationException;

@Component
@Order(1)
public class LengthValidator extends BaseValidator<Contrat> {

    @Override
    public void validate(Contrat contrat) {
        if (contrat.getDateFin().isBefore(contrat.getDateDebut()) ||
            contrat.getDateFin().isEqual(contrat.getDateDebut())) {
            throw new ValidationException("La date de fin doit être postérieure à la date de début.");
        }
        validateNext(contrat);
    }
}

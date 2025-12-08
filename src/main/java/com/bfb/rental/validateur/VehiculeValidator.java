package com.bfb.rental.validateur;


import com.bfb.rental.business.contrats.model.Contrat;
import jakarta.validation.ValidationException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class VehiculeValidator extends BaseValidator<Contrat> {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(VehiculeValidator.class);

    @Override
    public void validate(Contrat contrat) {
        if (contrat.getVehicule() == null) {
            throw new ValidationException("Le contrat doit avoir un véhicule assigné.");
        }

        if (contrat.getVehicule().getId() == null) {
            throw new ValidationException("Le véhicule doit avoir un identifiant valide.");
        }

        log.debug("Véhicule validé pour le contrat: {}", contrat.getId());
        validateNext(contrat);
    }
}

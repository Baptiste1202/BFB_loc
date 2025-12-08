package com.bfb.rental.validateur;


import com.bfb.rental.business.contrats.model.Contrat;
import jakarta.validation.ValidationException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ClientValidator extends BaseValidator<Contrat> {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ClientValidator.class);

    @Override
    public void validate(Contrat contrat) {
        if (contrat.getClient() == null) {
            throw new ValidationException("Le contrat doit avoir un client assigné.");
        }

        if (contrat.getClient().getId() == null) {
            throw new ValidationException("Le client doit avoir un identifiant valide.");
        }

        log.debug("Client validé pour le contrat: {}", contrat.getId());
        validateNext(contrat);
    }
}

package com.bfb.rental.interfaces.exceptions;

import java.util.List;

/**
 * JUSTIFICATION : Exception pour validation métier
 *
 * Différente de @Validated de Spring car :
 * ✅ Logique métier complexe (calculs, vérifications)
 * ✅ Plusieurs erreurs à remonter
 * ✅ Contexte métier (pourquoi c'est invalide)
 */
public class ValidationException extends BusinessException {

    private final List<String> errors;

    public ValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
        this.httpStatusCode = 400;
        this.errorCode = "VALIDATION_ERROR";
    }

    public List<String> getErrors() {
        return errors;
    }
}
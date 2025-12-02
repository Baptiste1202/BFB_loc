package com.bfb.rental.interfaces.exceptions;

/**
 * JUSTIFICATION : Exception spécifique pour ressource non trouvée
 *
 * EXTENDS BusinessException au lieu de RuntimeException car :
 * ✅ Cohérence : Tous les métier = BusinessException
 * ✅ Hiérarchie : Le catch d'une parent attend les exceptions des enfants
 * ✅ HTTP 404 : Automatiquement mappée à 404
 */
public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String resourceType, String identifier) {
        super(String.format("%s non trouvé : %s", resourceType, identifier));
        this.httpStatusCode = 404;
        this.errorCode = "RESOURCE_NOT_FOUND";
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.httpStatusCode = 404;
        this.errorCode = "RESOURCE_NOT_FOUND";
    }
}
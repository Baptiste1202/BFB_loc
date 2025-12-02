package com.bfb.rental.interfaces.exceptions;

/**
 * JUSTIFICATION : Exception métier personnalisée
 *
 * POURQUOI UNE EXCEPTION DÉDIÉE ?
 * ✅ Distinction : ClientNotFoundException ≠ DatabaseException
 * ✅ Catching : catch (BusinessException) = gérer tous les cas métier
 * ✅ Contexte : Ajouter des infos métier (client id, raison, etc.)
 * ✅ HTTP Mapping : BusinessException → 400/409 vs DatabaseException → 500
 *
 * PATTERN : Custom Exception Pattern
 * DESIGN PATTERN : Exception Hierarchy
 */
public abstract class BusinessException extends RuntimeException {

    protected int httpStatusCode;
    protected String errorCode;

    public BusinessException(String message) {
        super(message);
        this.httpStatusCode = 500;
        this.errorCode = "BUSINESS_ERROR";
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.httpStatusCode = 500;
        this.errorCode = "BUSINESS_ERROR";
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
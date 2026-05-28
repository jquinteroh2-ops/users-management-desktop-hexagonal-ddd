package com.jcaa.usersmanagement.domain.exception;

public final class InvalidEstadoMilitarException extends DomainException {

    private static final String MESSAGE =
            "Estado militar '%s' is invalid. Valid values are: ACTIVO, BAJA, RETIRADO.";

    private InvalidEstadoMilitarException(final String message) {
        super(message);
    }

    public static InvalidEstadoMilitarException becauseValueIsInvalid(final String value) {
        return new InvalidEstadoMilitarException(String.format(MESSAGE, value));
    }
}
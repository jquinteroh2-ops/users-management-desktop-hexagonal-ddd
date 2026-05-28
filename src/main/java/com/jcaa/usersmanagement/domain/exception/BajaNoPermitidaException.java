package com.jcaa.usersmanagement.domain.exception;

public final class BajaNoPermitidaException extends DomainException {

    private static final String MESSAGE =
            "Baja not allowed. Personal must be ACTIVO but current state is '%s'.";

    private BajaNoPermitidaException(final String message) {
        super(message);
    }

    public static BajaNoPermitidaException becausePersonalNoEstaActivo(final String estado) {
        return new BajaNoPermitidaException(String.format(MESSAGE, estado));
    }
}

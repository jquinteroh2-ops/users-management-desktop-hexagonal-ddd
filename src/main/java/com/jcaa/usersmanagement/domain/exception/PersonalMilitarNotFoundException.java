package com.jcaa.usersmanagement.domain.exception;

public final class PersonalMilitarNotFoundException extends DomainException {

    private static final String MESSAGE_BY_ID =
            "The personal militar with id '%s' was not found.";
    private static final String MESSAGE_BY_CEDULA =
            "The personal militar with cedula '%s' was not found.";

    private PersonalMilitarNotFoundException(final String message) {
        super(message);
    }

    public static PersonalMilitarNotFoundException becauseIdWasNotFound(final String id) {
        return new PersonalMilitarNotFoundException(String.format(MESSAGE_BY_ID, id));
    }

    public static PersonalMilitarNotFoundException becauseCedulaWasNotFound(final String cedula) {
        return new PersonalMilitarNotFoundException(String.format(MESSAGE_BY_CEDULA, cedula));
    }
}

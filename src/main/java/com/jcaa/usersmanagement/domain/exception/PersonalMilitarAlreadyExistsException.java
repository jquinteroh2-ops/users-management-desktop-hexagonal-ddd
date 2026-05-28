package com.jcaa.usersmanagement.domain.exception;

public final class PersonalMilitarAlreadyExistsException extends DomainException {

    private static final String MESSAGE =
            "Personal militar with cedula '%s' already exists.";

    private PersonalMilitarAlreadyExistsException(final String message) {
        super(message);
    }

    public static PersonalMilitarAlreadyExistsException becauseCedulaAlreadyExists(final String cedula) {
        return new PersonalMilitarAlreadyExistsException(String.format(MESSAGE, cedula));
    }
}

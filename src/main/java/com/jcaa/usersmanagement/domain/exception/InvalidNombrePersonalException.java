package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNombrePersonalException extends DomainException {

    private InvalidNombrePersonalException(final String message) {
        super(message);
    }

    public static InvalidNombrePersonalException becauseValueIsEmpty() {
        return new InvalidNombrePersonalException("NombrePersonal cannot be empty.");
    }

    public static InvalidNombrePersonalException becauseValueIsTooShort(final String value) {
        return new InvalidNombrePersonalException(
                String.format("NombrePersonal '%s' is too short. Minimum 2 characters required.", value));
    }
}

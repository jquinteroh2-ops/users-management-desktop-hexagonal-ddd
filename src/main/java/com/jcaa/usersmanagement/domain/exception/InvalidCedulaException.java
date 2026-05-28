package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCedulaException extends DomainException {

    private InvalidCedulaException(final String message) {
        super(message);
    }

    public static InvalidCedulaException becauseValueIsEmpty() {
        return new InvalidCedulaException("Cedula cannot be empty.");
    }

    public static InvalidCedulaException becauseFormatIsInvalid(final String value) {
        return new InvalidCedulaException(
                String.format("Cedula '%s' is invalid. Must contain 5 to 20 digits.", value));
    }
}

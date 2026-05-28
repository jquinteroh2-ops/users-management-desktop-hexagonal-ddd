package com.jcaa.usersmanagement.domain.exception;

public final class InvalidPersonalMilitarIdException extends DomainException {

    private InvalidPersonalMilitarIdException(final String message) {
        super(message);
    }

    public static InvalidPersonalMilitarIdException becauseValueIsEmpty() {
        return new InvalidPersonalMilitarIdException("PersonalMilitarId cannot be empty.");
    }
}

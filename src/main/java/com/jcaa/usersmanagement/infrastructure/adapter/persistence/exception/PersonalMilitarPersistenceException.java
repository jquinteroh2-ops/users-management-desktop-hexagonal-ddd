package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class PersonalMilitarPersistenceException extends RuntimeException {

    private PersonalMilitarPersistenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static PersonalMilitarPersistenceException becauseSaveFailed(
            final String id, final Throwable cause) {
        return new PersonalMilitarPersistenceException(
                "Failed to save personal militar with id: " + id, cause);
    }

    public static PersonalMilitarPersistenceException becauseUpdateFailed(
            final String id, final Throwable cause) {
        return new PersonalMilitarPersistenceException(
                "Failed to update personal militar with id: " + id, cause);
    }

    public static PersonalMilitarPersistenceException becauseFindByIdFailed(
            final String id, final Throwable cause) {
        return new PersonalMilitarPersistenceException(
                "Failed to find personal militar with id: " + id, cause);
    }

    public static PersonalMilitarPersistenceException becauseFindByCedulaFailed(
            final String cedula, final Throwable cause) {
        return new PersonalMilitarPersistenceException(
                "Failed to find personal militar with cedula: " + cedula, cause);
    }

    public static PersonalMilitarPersistenceException becauseFindByRangoFailed(
            final String rangoId, final Throwable cause) {
        return new PersonalMilitarPersistenceException(
                "Failed to find personal militar with rangoId: " + rangoId, cause);
    }

    public static PersonalMilitarPersistenceException becauseFindElegiblesFailed(final Throwable cause) {
        return new PersonalMilitarPersistenceException(
                "Failed to retrieve elegibles para ascenso", cause);
    }

    public static PersonalMilitarPersistenceException becauseFindAllFailed(final Throwable cause) {
        return new PersonalMilitarPersistenceException(
                "Failed to retrieve all personal militar", cause);
    }
}
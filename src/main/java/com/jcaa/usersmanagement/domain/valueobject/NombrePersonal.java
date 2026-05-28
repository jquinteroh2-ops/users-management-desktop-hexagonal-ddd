package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNombrePersonalException;
import java.util.Objects;

public record NombrePersonal(String value) {

    public NombrePersonal {
        final String normalized = Objects.requireNonNull(value, "NombrePersonal cannot be null").trim();
        if (normalized.isEmpty()) throw InvalidNombrePersonalException.becauseValueIsEmpty();
        if (normalized.length() < 2) throw InvalidNombrePersonalException.becauseValueIsTooShort(normalized);
        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}
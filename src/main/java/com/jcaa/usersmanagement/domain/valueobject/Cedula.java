package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidCedulaException;
import java.util.Objects;

public record Cedula(String value) {

    public Cedula {
        final String normalized = Objects.requireNonNull(value, "Cedula cannot be null").trim();
        if (normalized.isEmpty()) throw InvalidCedulaException.becauseValueIsEmpty();
        if (!normalized.matches("[0-9]{5,20}")) throw InvalidCedulaException.becauseFormatIsInvalid(normalized);
        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}
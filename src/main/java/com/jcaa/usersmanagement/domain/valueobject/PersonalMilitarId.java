package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidPersonalMilitarIdException;
import java.util.Objects;

public record PersonalMilitarId(String value) {

    public PersonalMilitarId {
        final String normalized = Objects.requireNonNull(value, "PersonalMilitarId cannot be null").trim();
        if (normalized.isEmpty()) throw InvalidPersonalMilitarIdException.becauseValueIsEmpty();
        value = normalized;
    }

    @Override
    public String toString() {
        return value;
    }
}
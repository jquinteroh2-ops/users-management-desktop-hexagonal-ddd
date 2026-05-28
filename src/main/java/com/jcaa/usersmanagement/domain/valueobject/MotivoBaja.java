package com.jcaa.usersmanagement.domain.valueobject;

import java.util.Objects;

public record MotivoBaja(String value) {

    public MotivoBaja {
        value = Objects.requireNonNullElse(value, "").trim();
    }

    @Override
    public String toString() {
        return value;
    }
}

package com.jcaa.usersmanagement.domain.enums;

import com.jcaa.usersmanagement.domain.exception.InvalidEstadoMilitarException;

public enum EstadoMilitar {
    ACTIVO, BAJA, RETIRADO;

    public static EstadoMilitar fromString(final String value) {
        for (final EstadoMilitar estado : values()) {
            if (estado.name().equalsIgnoreCase(value)) return estado;
        }
        throw InvalidEstadoMilitarException.becauseValueIsInvalid(value);
    }
}
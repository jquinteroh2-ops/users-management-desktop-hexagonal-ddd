package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PersonalMilitarMenuOption {

    REGISTRAR(1, "Registrar personal militar"),
    ASCENDER(2, "Ascender personal militar"),
    DAR_DE_BAJA(3, "Dar de baja personal militar"),
    LISTAR_POR_RANGO(4, "Listar personal por rango"),
    LISTAR_ELEGIBLES(5, "Listar elegibles para ascenso"),
    EXIT(0, "Exit");

    private final int number;
    private final String description;

    public static Optional<PersonalMilitarMenuOption> fromNumber(final int number) {
        for (final PersonalMilitarMenuOption option : values()) {
            if (option.number == number) {
                return Optional.of(option);
            }
        }
        return Optional.empty();
    }
}
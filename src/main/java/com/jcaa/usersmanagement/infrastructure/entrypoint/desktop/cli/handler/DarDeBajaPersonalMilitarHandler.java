package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.BajaNoPermitidaException;
import com.jcaa.usersmanagement.domain.exception.PersonalMilitarNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.PersonalMilitarResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.PersonalMilitarController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.DarDeBajaPersonalMilitarRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DarDeBajaPersonalMilitarHandler implements OperationHandler {

    private final PersonalMilitarController controller;
    private final ConsoleIO console;
    private final PersonalMilitarResponsePrinter printer;

    @Override
    public void handle() {
        final String id        = console.readRequired("  ID del personal        : ");
        final String motivo    = console.readOptional ("  Motivo de baja         : ");

        try {
            final var response = controller.darDeBaja(
                    new DarDeBajaPersonalMilitarRequest(id, motivo));
            console.println("\n  Personal dado de baja exitosamente.");
            printer.print(response);
        } catch (final BajaNoPermitidaException | PersonalMilitarNotFoundException e) {
            console.println("  Error: " + e.getMessage());
        }
    }
}
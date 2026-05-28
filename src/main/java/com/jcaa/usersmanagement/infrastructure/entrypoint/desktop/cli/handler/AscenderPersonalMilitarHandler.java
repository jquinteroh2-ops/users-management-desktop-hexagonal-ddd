package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.AscensoNoPermitidoException;
import com.jcaa.usersmanagement.domain.exception.PersonalMilitarNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.PersonalMilitarResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.PersonalMilitarController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AscenderPersonalMilitarRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AscenderPersonalMilitarHandler implements OperationHandler {

    private final PersonalMilitarController controller;
    private final ConsoleIO console;
    private final PersonalMilitarResponsePrinter printer;

    @Override
    public void handle() {
        final String id          = console.readRequired("  ID del personal        : ");
        final String nuevoRangoId = console.readRequired("  Nuevo Rango ID         : ");

        try {
            final var response = controller.ascender(
                    new AscenderPersonalMilitarRequest(id, nuevoRangoId));
            console.println("\n  Personal ascendido exitosamente.");
            printer.print(response);
        } catch (final AscensoNoPermitidoException | PersonalMilitarNotFoundException e) {
            console.println("  Error: " + e.getMessage());
        }
    }
}
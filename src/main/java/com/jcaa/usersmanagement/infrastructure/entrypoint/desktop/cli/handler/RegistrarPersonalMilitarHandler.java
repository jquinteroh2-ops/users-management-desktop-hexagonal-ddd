package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.PersonalMilitarAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.PersonalMilitarResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.PersonalMilitarController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RegistrarPersonalMilitarRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class RegistrarPersonalMilitarHandler implements OperationHandler {

    private final PersonalMilitarController controller;
    private final ConsoleIO console;
    private final PersonalMilitarResponsePrinter printer;

    @Override
    public void handle() {
        final String id          = console.readRequired("  ID (UUID)              : ");
        final String cedula      = console.readRequired("  Cedula                 : ");
        final String nombres     = console.readRequired("  Nombres                : ");
        final String apellidos   = console.readRequired("  Apellidos              : ");
        final String rangoId     = console.readRequired("  Rango ID               : ");
        final String fechaIngreso = console.readRequired("  Fecha Ingreso (yyyy-MM-dd): ");

        try {
            final var response = controller.registrar(
                    new RegistrarPersonalMilitarRequest(id, cedula, nombres, apellidos, rangoId, fechaIngreso));
            console.println("\n  Personal registrado exitosamente.");
            printer.print(response);
        } catch (final PersonalMilitarAlreadyExistsException e) {
            console.println("  Error: " + e.getMessage());
        }
    }
}
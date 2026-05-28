package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.PersonalMilitarResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.PersonalMilitarController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListarPersonalPorRangoHandler implements OperationHandler {

    private final PersonalMilitarController controller;
    private final ConsoleIO console;
    private final PersonalMilitarResponsePrinter printer;

    @Override
    public void handle() {
        final String rangoId = console.readRequired("  Rango ID               : ");
        printer.printList(controller.listarPorRango(rangoId));
    }
}
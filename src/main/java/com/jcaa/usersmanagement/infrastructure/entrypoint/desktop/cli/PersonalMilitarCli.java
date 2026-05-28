package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.AscenderPersonalMilitarHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DarDeBajaPersonalMilitarHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListarElegiblesParaAscensoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListarPersonalPorRangoHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.RegistrarPersonalMilitarHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.PersonalMilitarResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.PersonalMilitarMenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.PersonalMilitarController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PersonalMilitarCli {

    private static final String BANNER =
            """
            ==========================================
                 Personal Militar - Gestion
            ==========================================""";

    private static final String MENU_BORDER =
            "  ==========================================";

    private final PersonalMilitarController controller;
    private final ConsoleIO console;

    public void start() {
        console.println(BANNER);
        final PersonalMilitarResponsePrinter printer = new PersonalMilitarResponsePrinter(console);
        runLoop(buildHandlers(printer));
    }

    private void runLoop(final Map<PersonalMilitarMenuOption, OperationHandler> handlers) {
        boolean running = true;
        while (running) {
            printMenu();
            final int choice = console.readInt("\n  Opcion: ");
            final Optional<PersonalMilitarMenuOption> option =
                    PersonalMilitarMenuOption.fromNumber(choice);

            if (option.isEmpty()) {
                console.println("  Opcion invalida. Intente de nuevo.");
            } else if (option.get() == PersonalMilitarMenuOption.EXIT) {
                console.println("\n  Hasta luego!\n");
                running = false;
            } else {
                executeHandler(handlers, option.get());
            }
        }
    }

    private void executeHandler(
            final Map<PersonalMilitarMenuOption, OperationHandler> handlers,
            final PersonalMilitarMenuOption option) {
        try {
            handlers.get(option).handle();
        } catch (final ConstraintViolationException exception) {
            console.println("  Errores de validacion:");
            exception.getConstraintViolations()
                    .forEach(v -> console.println("    - " + v.getMessage()));
        } catch (final RuntimeException exception) {
            console.println("  Error: " + exception.getMessage());
        }
    }

    private Map<PersonalMilitarMenuOption, OperationHandler> buildHandlers(
            final PersonalMilitarResponsePrinter printer) {
        return Map.of(
                PersonalMilitarMenuOption.REGISTRAR,
                        new RegistrarPersonalMilitarHandler(controller, console, printer),
                PersonalMilitarMenuOption.ASCENDER,
                        new AscenderPersonalMilitarHandler(controller, console, printer),
                PersonalMilitarMenuOption.DAR_DE_BAJA,
                        new DarDeBajaPersonalMilitarHandler(controller, console, printer),
                PersonalMilitarMenuOption.LISTAR_POR_RANGO,
                        new ListarPersonalPorRangoHandler(controller, console, printer),
                PersonalMilitarMenuOption.LISTAR_ELEGIBLES,
                        new ListarElegiblesParaAscensoHandler(controller, console, printer));
    }

    private void printMenu() {
        console.println();
        console.println(MENU_BORDER);
        console.println("    Menu Personal Militar");
        console.println(MENU_BORDER);
        for (final PersonalMilitarMenuOption option : PersonalMilitarMenuOption.values()) {
            console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
        }
        console.println(MENU_BORDER);
    }
}
package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.PersonalMilitarResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PersonalMilitarResponsePrinter {

    private final ConsoleIO console;

    public void print(final PersonalMilitarResponse p) {
        console.println("\n  ----------------------------------------");
        console.println("  ID               : " + p.id());
        console.println("  Cedula           : " + p.cedula());
        console.println("  Nombres          : " + p.nombres());
        console.println("  Apellidos        : " + p.apellidos());
        console.println("  Rango ID         : " + p.rangoId());
        console.println("  Fecha Ingreso    : " + p.fechaIngreso());
        console.println("  Ultimo Ascenso   : " + p.fechaUltimoAscenso());
        console.println("  Estado           : " + p.estado());
        console.println("  Motivo Baja      : " + (p.motivoBaja().isBlank() ? "-" : p.motivoBaja()));
        console.println("  ----------------------------------------");
    }

    public void printList(final List<PersonalMilitarResponse> lista) {
        if (lista.isEmpty()) {
            console.println("  No hay personal registrado.");
            return;
        }
        lista.forEach(this::print);
    }
}
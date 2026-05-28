package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record PersonalMilitarResponse(
        String id,
        String cedula,
        String nombres,
        String apellidos,
        String rangoId,
        String fechaIngreso,
        String fechaUltimoAscenso,
        String estado,
        String motivoBaja
) {}
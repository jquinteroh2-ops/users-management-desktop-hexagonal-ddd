package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record RegistrarPersonalMilitarRequest(
        String id,
        String cedula,
        String nombres,
        String apellidos,
        String rangoId,
        String fechaIngreso
) {}
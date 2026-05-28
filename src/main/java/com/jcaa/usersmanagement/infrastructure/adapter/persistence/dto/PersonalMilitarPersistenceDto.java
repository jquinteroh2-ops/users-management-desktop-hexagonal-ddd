package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record PersonalMilitarPersistenceDto(
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
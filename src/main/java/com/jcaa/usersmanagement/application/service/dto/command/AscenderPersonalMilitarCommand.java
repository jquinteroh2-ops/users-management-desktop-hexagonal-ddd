package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record AscenderPersonalMilitarCommand(
        @NotBlank(message = "Id cannot be blank") String id,
        @NotBlank(message = "NuevoRangoId cannot be blank") String nuevoRangoId
) {}

package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegistrarPersonalMilitarCommand(
        @NotBlank(message = "Id cannot be blank") String id,
        @NotBlank(message = "Cedula cannot be blank")
        @Pattern(regexp = "\\d{5,20}", message = "Cedula must be 5-20 digits")
        String cedula,
        @NotBlank(message = "Nombres cannot be blank") String nombres,
        @NotBlank(message = "Apellidos cannot be blank") String apellidos,
        @NotBlank(message = "RangoId cannot be blank") String rangoId,
        @NotBlank(message = "FechaIngreso cannot be blank") String fechaIngreso
) {}
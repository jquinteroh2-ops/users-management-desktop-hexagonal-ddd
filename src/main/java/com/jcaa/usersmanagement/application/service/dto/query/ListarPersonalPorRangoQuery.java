package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotBlank;

public record ListarPersonalPorRangoQuery(
        @NotBlank(message = "RangoId cannot be blank") String rangoId
) {}

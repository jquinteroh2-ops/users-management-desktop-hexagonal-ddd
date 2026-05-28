package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.AscenderPersonalMilitarCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DarDeBajaPersonalMilitarCommand;
import com.jcaa.usersmanagement.application.service.dto.command.RegistrarPersonalMilitarCommand;
import com.jcaa.usersmanagement.application.service.dto.query.ListarPersonalPorRangoQuery;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AscenderPersonalMilitarRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.DarDeBajaPersonalMilitarRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.PersonalMilitarResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RegistrarPersonalMilitarRequest;
import java.util.List;

public final class PersonalMilitarDesktopMapper {

    private PersonalMilitarDesktopMapper() {}

    public static RegistrarPersonalMilitarCommand toRegistrarCommand(
            final RegistrarPersonalMilitarRequest request) {
        return new RegistrarPersonalMilitarCommand(
                request.id(),
                request.cedula(),
                request.nombres(),
                request.apellidos(),
                request.rangoId(),
                request.fechaIngreso());
    }

    public static AscenderPersonalMilitarCommand toAscenderCommand(
            final AscenderPersonalMilitarRequest request) {
        return new AscenderPersonalMilitarCommand(request.id(), request.nuevoRangoId());
    }

    public static DarDeBajaPersonalMilitarCommand toDarDeBajaCommand(
            final DarDeBajaPersonalMilitarRequest request) {
        return new DarDeBajaPersonalMilitarCommand(request.id(), request.motivoBaja());
    }

    public static ListarPersonalPorRangoQuery toListarPorRangoQuery(final String rangoId) {
        return new ListarPersonalPorRangoQuery(rangoId);
    }

    public static PersonalMilitarResponse toResponse(final PersonalMilitarModel personal) {
        return new PersonalMilitarResponse(
                personal.getId().value(),
                personal.getCedula().value(),
                personal.getNombres().value(),
                personal.getApellidos().value(),
                personal.getRangoId().value(),
                personal.getFechaIngreso().toString(),
                personal.getFechaUltimoAscenso().toString(),
                personal.getEstado().name(),
                personal.getMotivoBaja().value());
    }

    public static List<PersonalMilitarResponse> toResponseList(
            final List<PersonalMilitarModel> lista) {
        return lista.stream().map(PersonalMilitarDesktopMapper::toResponse).toList();
    }
}
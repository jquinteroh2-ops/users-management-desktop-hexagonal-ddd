package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.AscenderPersonalMilitarUseCase;
import com.jcaa.usersmanagement.application.port.in.ConsultarMilitaresElegiblesParaAscensoUseCase;
import com.jcaa.usersmanagement.application.port.in.DarDeBajaPersonalMilitarUseCase;
import com.jcaa.usersmanagement.application.port.in.ListarPersonalPorRangoUseCase;
import com.jcaa.usersmanagement.application.port.in.RegistrarPersonalMilitarUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AscenderPersonalMilitarRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.DarDeBajaPersonalMilitarRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.PersonalMilitarResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.RegistrarPersonalMilitarRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.PersonalMilitarDesktopMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PersonalMilitarController {

    private final RegistrarPersonalMilitarUseCase registrarUseCase;
    private final AscenderPersonalMilitarUseCase ascenderUseCase;
    private final DarDeBajaPersonalMilitarUseCase darDeBajaUseCase;
    private final ListarPersonalPorRangoUseCase listarPorRangoUseCase;
    private final ConsultarMilitaresElegiblesParaAscensoUseCase elegiblesUseCase;

    public PersonalMilitarResponse registrar(final RegistrarPersonalMilitarRequest request) {
        return PersonalMilitarDesktopMapper.toResponse(
                registrarUseCase.execute(PersonalMilitarDesktopMapper.toRegistrarCommand(request)));
    }

    public PersonalMilitarResponse ascender(final AscenderPersonalMilitarRequest request) {
        return PersonalMilitarDesktopMapper.toResponse(
                ascenderUseCase.execute(PersonalMilitarDesktopMapper.toAscenderCommand(request)));
    }

    public PersonalMilitarResponse darDeBaja(final DarDeBajaPersonalMilitarRequest request) {
        return PersonalMilitarDesktopMapper.toResponse(
                darDeBajaUseCase.execute(PersonalMilitarDesktopMapper.toDarDeBajaCommand(request)));
    }

    public List<PersonalMilitarResponse> listarPorRango(final String rangoId) {
        return PersonalMilitarDesktopMapper.toResponseList(
                listarPorRangoUseCase.execute(PersonalMilitarDesktopMapper.toListarPorRangoQuery(rangoId)));
    }

    public List<PersonalMilitarResponse> listarElegibles() {
        return PersonalMilitarDesktopMapper.toResponseList(elegiblesUseCase.execute());
    }
}
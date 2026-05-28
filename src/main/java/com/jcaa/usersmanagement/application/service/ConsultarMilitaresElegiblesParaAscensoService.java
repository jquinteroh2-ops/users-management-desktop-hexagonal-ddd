package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.ConsultarMilitaresElegiblesParaAscensoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPersonalMilitarElegiblesParaAscensoPort;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ConsultarMilitaresElegiblesParaAscensoService
        implements ConsultarMilitaresElegiblesParaAscensoUseCase {

    private final GetPersonalMilitarElegiblesParaAscensoPort getElegiblesPort;

    @Override
    public List<PersonalMilitarModel> execute() {
        return getElegiblesPort.getElegibles();
    }
}
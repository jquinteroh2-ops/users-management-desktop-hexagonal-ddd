package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import java.util.List;

public interface ConsultarMilitaresElegiblesParaAscensoUseCase {
    List<PersonalMilitarModel> execute();
}

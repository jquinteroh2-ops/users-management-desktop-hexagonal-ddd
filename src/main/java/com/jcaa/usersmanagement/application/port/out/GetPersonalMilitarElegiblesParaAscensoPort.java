package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import java.util.List;

public interface GetPersonalMilitarElegiblesParaAscensoPort {
    List<PersonalMilitarModel> getElegibles();
}
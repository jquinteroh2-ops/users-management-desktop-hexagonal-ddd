package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.Cedula;
import java.util.Optional;

public interface GetPersonalMilitarByCedulaPort {
    Optional<PersonalMilitarModel> getByCedula(Cedula cedula);
}

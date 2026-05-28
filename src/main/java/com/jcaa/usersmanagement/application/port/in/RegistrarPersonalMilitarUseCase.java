package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.RegistrarPersonalMilitarCommand;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;

public interface RegistrarPersonalMilitarUseCase {
    PersonalMilitarModel execute(RegistrarPersonalMilitarCommand command);
}

package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DarDeBajaPersonalMilitarCommand;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;

public interface DarDeBajaPersonalMilitarUseCase {
    PersonalMilitarModel execute(DarDeBajaPersonalMilitarCommand command);
}

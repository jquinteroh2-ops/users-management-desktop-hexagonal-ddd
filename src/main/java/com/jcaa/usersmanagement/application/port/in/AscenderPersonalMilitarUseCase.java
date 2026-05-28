package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.AscenderPersonalMilitarCommand;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;

public interface AscenderPersonalMilitarUseCase {
    PersonalMilitarModel execute(AscenderPersonalMilitarCommand command);
}

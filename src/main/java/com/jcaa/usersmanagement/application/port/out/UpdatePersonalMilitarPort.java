package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;

public interface UpdatePersonalMilitarPort {
    PersonalMilitarModel update(PersonalMilitarModel personal);
}

package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;

public interface SavePersonalMilitarPort {
    PersonalMilitarModel save(PersonalMilitarModel personal);
}

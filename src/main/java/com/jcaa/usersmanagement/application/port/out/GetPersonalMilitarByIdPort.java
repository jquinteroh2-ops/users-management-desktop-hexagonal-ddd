package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.PersonalMilitarId;
import java.util.Optional;

public interface GetPersonalMilitarByIdPort {
    Optional<PersonalMilitarModel> getById(PersonalMilitarId id);
}

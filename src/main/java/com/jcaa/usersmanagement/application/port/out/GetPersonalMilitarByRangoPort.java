package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import java.util.List;

public interface GetPersonalMilitarByRangoPort {
    List<PersonalMilitarModel> getByRango(RangoId rangoId);
}

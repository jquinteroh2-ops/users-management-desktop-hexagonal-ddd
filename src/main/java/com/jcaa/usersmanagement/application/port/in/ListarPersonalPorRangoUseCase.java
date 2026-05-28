package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.ListarPersonalPorRangoQuery;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import java.util.List;

public interface ListarPersonalPorRangoUseCase {
    List<PersonalMilitarModel> execute(ListarPersonalPorRangoQuery query);
}

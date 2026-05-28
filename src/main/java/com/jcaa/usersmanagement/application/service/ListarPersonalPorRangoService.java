package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.ListarPersonalPorRangoUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPersonalMilitarByRangoPort;
import com.jcaa.usersmanagement.application.service.dto.query.ListarPersonalPorRangoQuery;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListarPersonalPorRangoService implements ListarPersonalPorRangoUseCase {

    private final GetPersonalMilitarByRangoPort getPersonalMilitarByRangoPort;
    private final Validator validator;

    @Override
    public List<PersonalMilitarModel> execute(final ListarPersonalPorRangoQuery query) {
        final Set<ConstraintViolation<ListarPersonalPorRangoQuery>> violations =
                validator.validate(query);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return getPersonalMilitarByRangoPort.getByRango(new RangoId(query.rangoId()));
    }
}
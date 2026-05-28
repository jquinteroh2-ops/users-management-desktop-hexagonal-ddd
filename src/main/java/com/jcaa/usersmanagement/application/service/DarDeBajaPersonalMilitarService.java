package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DarDeBajaPersonalMilitarUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPersonalMilitarByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdatePersonalMilitarPort;
import com.jcaa.usersmanagement.application.service.dto.command.DarDeBajaPersonalMilitarCommand;
import com.jcaa.usersmanagement.domain.enums.EstadoMilitar;
import com.jcaa.usersmanagement.domain.exception.BajaNoPermitidaException;
import com.jcaa.usersmanagement.domain.exception.PersonalMilitarNotFoundException;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.MotivoBaja;
import com.jcaa.usersmanagement.domain.valueobject.PersonalMilitarId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DarDeBajaPersonalMilitarService implements DarDeBajaPersonalMilitarUseCase {

    private final GetPersonalMilitarByIdPort getPersonalMilitarByIdPort;
    private final UpdatePersonalMilitarPort updatePersonalMilitarPort;
    private final Validator validator;

    @Override
    public PersonalMilitarModel execute(final DarDeBajaPersonalMilitarCommand command) {
        validateCommand(command);

        final PersonalMilitarModel personal = getPersonalMilitarByIdPort
                .getById(new PersonalMilitarId(command.id()))
                .orElseThrow(() -> PersonalMilitarNotFoundException.becauseIdWasNotFound(command.id()));

        if (personal.getEstado() != EstadoMilitar.ACTIVO) {
            throw BajaNoPermitidaException.becausePersonalNoEstaActivo(personal.getEstado().name());
        }

        final PersonalMilitarModel dado_de_baja = PersonalMilitarModel.create(
                personal.getId(),
                personal.getCedula(),
                personal.getNombres(),
                personal.getApellidos(),
                personal.getRangoId(),
                personal.getFechaIngreso(),
                personal.getFechaUltimoAscenso(),
                EstadoMilitar.BAJA,
                new MotivoBaja(command.motivoBaja() != null ? command.motivoBaja() : ""));

        return updatePersonalMilitarPort.update(dado_de_baja);
    }

    private void validateCommand(final DarDeBajaPersonalMilitarCommand command) {
        final Set<ConstraintViolation<DarDeBajaPersonalMilitarCommand>> violations =
                validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.AscenderPersonalMilitarUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPersonalMilitarByIdPort;
import com.jcaa.usersmanagement.application.port.out.GetRangoMilitarByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdatePersonalMilitarPort;
import com.jcaa.usersmanagement.application.service.dto.command.AscenderPersonalMilitarCommand;
import com.jcaa.usersmanagement.domain.enums.EstadoMilitar;
import com.jcaa.usersmanagement.domain.exception.AscensoNoPermitidoException;
import com.jcaa.usersmanagement.domain.exception.PersonalMilitarNotFoundException;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.domain.model.RangoMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.PersonalMilitarId;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AscenderPersonalMilitarService implements AscenderPersonalMilitarUseCase {

    private final GetPersonalMilitarByIdPort getPersonalMilitarByIdPort;
    private final GetRangoMilitarByIdPort getRangoMilitarByIdPort;
    private final UpdatePersonalMilitarPort updatePersonalMilitarPort;
    private final Validator validator;

    @Override
    public PersonalMilitarModel execute(final AscenderPersonalMilitarCommand command) {
        validateCommand(command);

        final PersonalMilitarModel personal = getPersonalMilitarByIdPort
                .getById(new PersonalMilitarId(command.id()))
                .orElseThrow(() -> PersonalMilitarNotFoundException.becauseIdWasNotFound(command.id()));

        ensurePersonalEstaActivo(personal);

        final RangoMilitarModel rangoActual = getRangoMilitarByIdPort
                .getById(personal.getRangoId())
                .orElseThrow(() -> AscensoNoPermitidoException.becauseNuevoRangoNoExiste(personal.getRangoId().value()));

        ensureTiempoMinimoAscensoCumplido(personal, rangoActual);

        final RangoId nuevoRangoId = new RangoId(command.nuevoRangoId());
        getRangoMilitarByIdPort
                .getById(nuevoRangoId)
                .orElseThrow(() -> AscensoNoPermitidoException.becauseNuevoRangoNoExiste(command.nuevoRangoId()));

        final PersonalMilitarModel ascendido = PersonalMilitarModel.create(
                personal.getId(),
                personal.getCedula(),
                personal.getNombres(),
                personal.getApellidos(),
                nuevoRangoId,
                personal.getFechaIngreso(),
                LocalDate.now(),
                EstadoMilitar.ACTIVO,
                personal.getMotivoBaja());

        return updatePersonalMilitarPort.update(ascendido);
    }

    private void validateCommand(final AscenderPersonalMilitarCommand command) {
        final Set<ConstraintViolation<AscenderPersonalMilitarCommand>> violations =
                validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensurePersonalEstaActivo(final PersonalMilitarModel personal) {
        if (personal.getEstado() != EstadoMilitar.ACTIVO) {
            throw AscensoNoPermitidoException.becausePersonalNoEstaActivo(personal.getEstado().name());
        }
    }

    private void ensureTiempoMinimoAscensoCumplido(
            final PersonalMilitarModel personal, final RangoMilitarModel rangoActual) {
        final long mesesEnRango = ChronoUnit.MONTHS.between(
                personal.getFechaUltimoAscenso(), LocalDate.now());
        final int mesesRequeridos = rangoActual.getTiempoMinimoAscenso().meses();
        if (mesesEnRango < mesesRequeridos) {
            throw AscensoNoPermitidoException.becauseTiempoMinimoNoCumplido(mesesEnRango, mesesRequeridos);
        }
    }
}
package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.RegistrarPersonalMilitarUseCase;
import com.jcaa.usersmanagement.application.port.out.GetPersonalMilitarByCedulaPort;
import com.jcaa.usersmanagement.application.port.out.GetRangoMilitarByIdPort;
import com.jcaa.usersmanagement.application.port.out.SavePersonalMilitarPort;
import com.jcaa.usersmanagement.application.service.dto.command.RegistrarPersonalMilitarCommand;
import com.jcaa.usersmanagement.application.service.mapper.PersonalMilitarApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.PersonalMilitarAlreadyExistsException;
import com.jcaa.usersmanagement.domain.exception.AscensoNoPermitidoException;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.Cedula;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class RegistrarPersonalMilitarService implements RegistrarPersonalMilitarUseCase {

    private final SavePersonalMilitarPort savePersonalMilitarPort;
    private final GetPersonalMilitarByCedulaPort getPersonalMilitarByCedulaPort;
    private final GetRangoMilitarByIdPort getRangoMilitarByIdPort;
    private final Validator validator;

    @Override
    public PersonalMilitarModel execute(final RegistrarPersonalMilitarCommand command) {
        validateCommand(command);
        ensureCedulaIsNotTaken(new Cedula(command.cedula()));
        ensureRangoExists(new RangoId(command.rangoId()));
        ensureFechaIngresoIsNotFuture(LocalDate.parse(command.fechaIngreso()));
        final PersonalMilitarModel personal =
                PersonalMilitarApplicationMapper.fromRegistrarCommandToModel(command);
        return savePersonalMilitarPort.save(personal);
    }

    private void validateCommand(final RegistrarPersonalMilitarCommand command) {
        final Set<ConstraintViolation<RegistrarPersonalMilitarCommand>> violations =
                validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureCedulaIsNotTaken(final Cedula cedula) {
        getPersonalMilitarByCedulaPort
                .getByCedula(cedula)
                .ifPresent(ignored -> {
                    throw PersonalMilitarAlreadyExistsException.becauseCedulaAlreadyExists(cedula.value());
                });
    }

    private void ensureRangoExists(final RangoId rangoId) {
        getRangoMilitarByIdPort
                .getById(rangoId)
                .orElseThrow(() -> AscensoNoPermitidoException.becauseNuevoRangoNoExiste(rangoId.value()));
    }

    private void ensureFechaIngresoIsNotFuture(final LocalDate fechaIngreso) {
        if (fechaIngreso.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser futura: " + fechaIngreso);
        }
    }
}
package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.RegistrarPersonalMilitarCommand;
import com.jcaa.usersmanagement.domain.enums.EstadoMilitar;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.Cedula;
import com.jcaa.usersmanagement.domain.valueobject.MotivoBaja;
import com.jcaa.usersmanagement.domain.valueobject.NombrePersonal;
import com.jcaa.usersmanagement.domain.valueobject.PersonalMilitarId;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import java.time.LocalDate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonalMilitarApplicationMapper {

    public PersonalMilitarModel fromRegistrarCommandToModel(final RegistrarPersonalMilitarCommand command) {
        final LocalDate fechaIngreso = LocalDate.parse(command.fechaIngreso());
        return PersonalMilitarModel.create(
                new PersonalMilitarId(command.id()),
                new Cedula(command.cedula()),
                new NombrePersonal(command.nombres()),
                new NombrePersonal(command.apellidos()),
                new RangoId(command.rangoId()),
                fechaIngreso,
                fechaIngreso,
                EstadoMilitar.ACTIVO,
                new MotivoBaja(""));
    }
}
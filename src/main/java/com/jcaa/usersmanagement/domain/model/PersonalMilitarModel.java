package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.EstadoMilitar;
import com.jcaa.usersmanagement.domain.valueobject.Cedula;
import com.jcaa.usersmanagement.domain.valueobject.MotivoBaja;
import com.jcaa.usersmanagement.domain.valueobject.NombrePersonal;
import com.jcaa.usersmanagement.domain.valueobject.PersonalMilitarId;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import java.time.LocalDate;
import lombok.Value;

@Value
public class PersonalMilitarModel {

    PersonalMilitarId id;
    Cedula            cedula;
    NombrePersonal    nombres;
    NombrePersonal    apellidos;
    RangoId           rangoId;
    LocalDate         fechaIngreso;
    LocalDate         fechaUltimoAscenso;
    EstadoMilitar     estado;
    MotivoBaja        motivoBaja;

    public static PersonalMilitarModel create(
            final PersonalMilitarId id,
            final Cedula cedula,
            final NombrePersonal nombres,
            final NombrePersonal apellidos,
            final RangoId rangoId,
            final LocalDate fechaIngreso,
            final LocalDate fechaUltimoAscenso,
            final EstadoMilitar estado,
            final MotivoBaja motivoBaja) {
        return new PersonalMilitarModel(
                id, cedula, nombres, apellidos, rangoId,
                fechaIngreso, fechaUltimoAscenso, estado, motivoBaja);
    }
}

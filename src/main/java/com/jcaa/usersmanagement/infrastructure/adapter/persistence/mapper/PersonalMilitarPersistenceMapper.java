package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.EstadoMilitar;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.Cedula;
import com.jcaa.usersmanagement.domain.valueobject.MotivoBaja;
import com.jcaa.usersmanagement.domain.valueobject.NombrePersonal;
import com.jcaa.usersmanagement.domain.valueobject.PersonalMilitarId;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.PersonalMilitarPersistenceDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonalMilitarPersistenceMapper {

    public PersonalMilitarPersistenceDto fromModelToDto(final PersonalMilitarModel personal) {
        return new PersonalMilitarPersistenceDto(
                personal.getId().value(),
                personal.getCedula().value(),
                personal.getNombres().value(),
                personal.getApellidos().value(),
                personal.getRangoId().value(),
                personal.getFechaIngreso().toString(),
                personal.getFechaUltimoAscenso().toString(),
                personal.getEstado().name(),
                personal.getMotivoBaja().value());
    }

    public PersonalMilitarModel fromResultSetToModel(final ResultSet rs) throws SQLException {
        return new PersonalMilitarModel(
                new PersonalMilitarId(rs.getString("id")),
                new Cedula(rs.getString("cedula")),
                new NombrePersonal(rs.getString("nombres")),
                new NombrePersonal(rs.getString("apellidos")),
                new RangoId(rs.getString("rango_id")),
                LocalDate.parse(rs.getString("fecha_ingreso")),
                LocalDate.parse(rs.getString("fecha_ultimo_ascenso")),
                EstadoMilitar.fromString(rs.getString("estado")),
                new MotivoBaja(rs.getString("motivo_baja") != null ? rs.getString("motivo_baja") : ""));
    }

    public List<PersonalMilitarModel> fromResultSetToModelList(final ResultSet rs) throws SQLException {
        final List<PersonalMilitarModel> result = new ArrayList<>();
        while (rs.next()) {
            result.add(fromResultSetToModel(rs));
        }
        return result;
    }
}
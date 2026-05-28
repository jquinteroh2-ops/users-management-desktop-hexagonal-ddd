package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.GetAllPersonalMilitarPort;
import com.jcaa.usersmanagement.application.port.out.GetPersonalMilitarByCedulaPort;
import com.jcaa.usersmanagement.application.port.out.GetPersonalMilitarByIdPort;
import com.jcaa.usersmanagement.application.port.out.GetPersonalMilitarByRangoPort;
import com.jcaa.usersmanagement.application.port.out.GetPersonalMilitarElegiblesParaAscensoPort;
import com.jcaa.usersmanagement.application.port.out.SavePersonalMilitarPort;
import com.jcaa.usersmanagement.application.port.out.UpdatePersonalMilitarPort;
import com.jcaa.usersmanagement.domain.exception.PersonalMilitarNotFoundException;
import com.jcaa.usersmanagement.domain.model.PersonalMilitarModel;
import com.jcaa.usersmanagement.domain.valueobject.Cedula;
import com.jcaa.usersmanagement.domain.valueobject.PersonalMilitarId;
import com.jcaa.usersmanagement.domain.valueobject.RangoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.PersonalMilitarPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersonalMilitarPersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.PersonalMilitarPersistenceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PersonalMilitarRepositoryMySQL
        implements SavePersonalMilitarPort,
        UpdatePersonalMilitarPort,
        GetPersonalMilitarByIdPort,
        GetPersonalMilitarByCedulaPort,
        GetPersonalMilitarByRangoPort,
        GetPersonalMilitarElegiblesParaAscensoPort,
        GetAllPersonalMilitarPort {

    private static final String SQL_INSERT =
            "INSERT INTO personal_militar (id, cedula, nombres, apellidos, rango_id, "
                    + "fecha_ingreso, fecha_ultimo_ascenso, estado, motivo_baja) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE personal_militar SET cedula = ?, nombres = ?, apellidos = ?, rango_id = ?, "
                    + "fecha_ingreso = ?, fecha_ultimo_ascenso = ?, estado = ?, motivo_baja = ? "
                    + "WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, cedula, nombres, apellidos, rango_id, fecha_ingreso, "
                    + "fecha_ultimo_ascenso, estado, motivo_baja "
                    + "FROM personal_militar WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_BY_CEDULA =
            "SELECT id, cedula, nombres, apellidos, rango_id, fecha_ingreso, "
                    + "fecha_ultimo_ascenso, estado, motivo_baja "
                    + "FROM personal_militar WHERE cedula = ? LIMIT 1";

    private static final String SQL_SELECT_BY_RANGO =
            "SELECT id, cedula, nombres, apellidos, rango_id, fecha_ingreso, "
                    + "fecha_ultimo_ascenso, estado, motivo_baja "
                    + "FROM personal_militar WHERE rango_id = ? ORDER BY apellidos ASC";

    private static final String SQL_SELECT_ALL =
            "SELECT id, cedula, nombres, apellidos, rango_id, fecha_ingreso, "
                    + "fecha_ultimo_ascenso, estado, motivo_baja "
                    + "FROM personal_militar ORDER BY apellidos ASC";

    private static final String SQL_SELECT_ELEGIBLES =
            "SELECT pm.id, pm.cedula, pm.nombres, pm.apellidos, pm.rango_id, pm.fecha_ingreso, "
                    + "pm.fecha_ultimo_ascenso, pm.estado, pm.motivo_baja "
                    + "FROM personal_militar pm "
                    + "JOIN rangos_militares rm ON pm.rango_id = rm.id "
                    + "WHERE pm.estado = 'ACTIVO' "
                    + "AND TIMESTAMPDIFF(MONTH, pm.fecha_ultimo_ascenso, NOW()) >= rm.tiempo_minimo_ascenso_meses "
                    + "ORDER BY pm.apellidos ASC";

    private final Connection connection;

    @Override
    public PersonalMilitarModel save(final PersonalMilitarModel personal) {
        final PersonalMilitarPersistenceDto dto = PersonalMilitarPersistenceMapper.fromModelToDto(personal);
        executeSave(dto);
        return findByIdOrFail(personal.getId());
    }

    @Override
    public PersonalMilitarModel update(final PersonalMilitarModel personal) {
        final PersonalMilitarPersistenceDto dto = PersonalMilitarPersistenceMapper.fromModelToDto(personal);
        executeUpdate(dto);
        return findByIdOrFail(personal.getId());
    }

    @Override
    public Optional<PersonalMilitarModel> getById(final PersonalMilitarId id) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            st.setString(1, id.value());
            final ResultSet rs = st.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(PersonalMilitarPersistenceMapper.fromResultSetToModel(rs));
        } catch (final SQLException e) {
            throw PersonalMilitarPersistenceException.becauseFindByIdFailed(id.value(), e);
        }
    }

    @Override
    public Optional<PersonalMilitarModel> getByCedula(final Cedula cedula) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_BY_CEDULA)) {
            st.setString(1, cedula.value());
            final ResultSet rs = st.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(PersonalMilitarPersistenceMapper.fromResultSetToModel(rs));
        } catch (final SQLException e) {
            throw PersonalMilitarPersistenceException.becauseFindByCedulaFailed(cedula.value(), e);
        }
    }

    @Override
    public List<PersonalMilitarModel> getByRango(final RangoId rangoId) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_BY_RANGO)) {
            st.setString(1, rangoId.value());
            final ResultSet rs = st.executeQuery();
            return PersonalMilitarPersistenceMapper.fromResultSetToModelList(rs);
        } catch (final SQLException e) {
            throw PersonalMilitarPersistenceException.becauseFindByRangoFailed(rangoId.value(), e);
        }
    }

    @Override
    public List<PersonalMilitarModel> getElegibles() {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_ELEGIBLES)) {
            final ResultSet rs = st.executeQuery();
            return PersonalMilitarPersistenceMapper.fromResultSetToModelList(rs);
        } catch (final SQLException e) {
            throw PersonalMilitarPersistenceException.becauseFindElegiblesFailed(e);
        }
    }

    @Override
    public List<PersonalMilitarModel> getAll() {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet rs = st.executeQuery();
            return PersonalMilitarPersistenceMapper.fromResultSetToModelList(rs);
        } catch (final SQLException e) {
            throw PersonalMilitarPersistenceException.becauseFindAllFailed(e);
        }
    }

    private void executeSave(final PersonalMilitarPersistenceDto dto) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_INSERT)) {
            st.setString(1, dto.id());
            st.setString(2, dto.cedula());
            st.setString(3, dto.nombres());
            st.setString(4, dto.apellidos());
            st.setString(5, dto.rangoId());
            st.setString(6, dto.fechaIngreso());
            st.setString(7, dto.fechaUltimoAscenso());
            st.setString(8, dto.estado());
            st.setString(9, dto.motivoBaja());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw PersonalMilitarPersistenceException.becauseSaveFailed(dto.id(), e);
        }
    }

    private void executeUpdate(final PersonalMilitarPersistenceDto dto) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_UPDATE)) {
            st.setString(1, dto.cedula());
            st.setString(2, dto.nombres());
            st.setString(3, dto.apellidos());
            st.setString(4, dto.rangoId());
            st.setString(5, dto.fechaIngreso());
            st.setString(6, dto.fechaUltimoAscenso());
            st.setString(7, dto.estado());
            st.setString(8, dto.motivoBaja());
            st.setString(9, dto.id());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw PersonalMilitarPersistenceException.becauseUpdateFailed(dto.id(), e);
        }
    }

    private PersonalMilitarModel findByIdOrFail(final PersonalMilitarId id) {
        return getById(id)
                .orElseThrow(() -> PersonalMilitarNotFoundException.becauseIdWasNotFound(id.value()));
    }
}
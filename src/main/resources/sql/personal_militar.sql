-- Tabla: personal_militar
-- Depende de: rangos_militares (debe ejecutarse primero rangos_militares.sql)

CREATE TABLE IF NOT EXISTS personal_militar (
    id                    VARCHAR(36)  PRIMARY KEY,
    cedula                VARCHAR(20)  UNIQUE NOT NULL,
    nombres               VARCHAR(100) NOT NULL,
    apellidos             VARCHAR(100) NOT NULL,
    rango_id              VARCHAR(36)  NOT NULL,
    fecha_ingreso         DATE         NOT NULL,
    fecha_ultimo_ascenso  DATE         NOT NULL,
    estado                VARCHAR(20)  NOT NULL DEFAULT 'ACTIVO',
    motivo_baja           VARCHAR(500),
    created_at            DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at            DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_personal_rango FOREIGN KEY (rango_id) REFERENCES rangos_militares(id),
    CONSTRAINT chk_estado CHECK (estado IN ('ACTIVO','BAJA','RETIRADO'))
);
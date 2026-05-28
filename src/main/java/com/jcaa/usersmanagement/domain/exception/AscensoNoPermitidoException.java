package com.jcaa.usersmanagement.domain.exception;

public final class AscensoNoPermitidoException extends DomainException {

    private AscensoNoPermitidoException(final String message) {
        super(message);
    }

    public static AscensoNoPermitidoException becauseTiempoMinimoNoCumplido(
            final long mesesActuales, final int mesesRequeridos) {
        return new AscensoNoPermitidoException(String.format(
                "Ascenso not allowed. Military has %d months in current rank but %d are required.",
                mesesActuales, mesesRequeridos));
    }

    public static AscensoNoPermitidoException becauseNuevoRangoNoExiste(final String rangoId) {
        return new AscensoNoPermitidoException(
                String.format("Ascenso not allowed. New rango with id '%s' does not exist.", rangoId));
    }

    public static AscensoNoPermitidoException becausePersonalNoEstaActivo(final String estado) {
        return new AscensoNoPermitidoException(
                String.format("Ascenso not allowed. Personal must be ACTIVO but is '%s'.", estado));
    }
}

package com.sergiobogarin.conversordedivisas.exceptions;

public class ErrorQuotaReached extends RuntimeException {
    private String mensaje;

    public ErrorQuotaReached(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}

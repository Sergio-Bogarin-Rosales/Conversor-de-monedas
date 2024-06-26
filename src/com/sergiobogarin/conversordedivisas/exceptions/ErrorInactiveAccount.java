package com.sergiobogarin.conversordedivisas.exceptions;

public class ErrorInactiveAccount extends RuntimeException {
    private String mensaje;

    public ErrorInactiveAccount(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}

package com.sergiobogarin.conversordedivisas.exceptions;

public class ErrorInvalidKey extends RuntimeException {

    private String mensaje;

    public ErrorInvalidKey(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}

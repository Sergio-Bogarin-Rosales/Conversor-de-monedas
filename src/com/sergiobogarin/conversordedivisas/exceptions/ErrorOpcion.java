package com.sergiobogarin.conversordedivisas.exceptions;

public class ErrorOpcion extends RuntimeException {

    private String mensaje;

    public ErrorOpcion(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }
}

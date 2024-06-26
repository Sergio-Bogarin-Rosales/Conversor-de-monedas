package com.sergiobogarin.conversordedivisas.exceptions;

public class ErrorMalformedRequest extends RuntimeException {

    private String mensaje;

    public ErrorMalformedRequest(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }

}

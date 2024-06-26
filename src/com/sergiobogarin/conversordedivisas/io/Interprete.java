package com.sergiobogarin.conversordedivisas.io;

import java.util.List;
import java.util.Scanner;

import com.sergiobogarin.conversordedivisas.exceptions.ErrorOpcion;
import com.sergiobogarin.conversordedivisas.modelos.MercadoDeDivisas;

import java.lang.NumberFormatException;

public class Interprete {
    Scanner lectura = new Scanner(System.in);

    public void espera() {
        lectura.nextLine();
    }

    public void imprimirMenu(String siglasDivisaBase, String siglasDivisaDestino, String monto, String montoEquivalente,
            String fecha) {

        System.out.println(
                "| ---------- Conversor de divisas ---------- ( " + fecha + " )| \n");
        System.out
                .println("   " + siglasDivisaBase + " : " + monto + "  --> " + siglasDivisaDestino + " : "
                        + montoEquivalente);
        // System.out.println(" 1) Checar mercado de divisas");
        // System.out.println(" 2) Elegir divisa base");
        // System.out.println(" 3) Elegir divisa a comvertir");
        // System.out.println(" 4) Establecer monto a comvertir");
        // System.out.println(" 5) Convertir divisa");
    }

    public void imprimirMenu(String siglasDivisaBase, String siglasDivisaDestino, String monto) {

        System.out.println("| ---------- Conversor de divisas ---------- |");
        System.out.println("  " + siglasDivisaBase + " : " + monto + "  --> " + siglasDivisaDestino + "\n");
        System.out.println("   1) Checar mercado de divisas");
        System.out.println("   2) Elegir divisa base");
        System.out.println("   3) Elegir divisa a convertir");
        System.out.println("   4) Establecer monto a convertir");
        System.out.println("   5) Convertir divisa \n");
    }

    public int pedirOpcion() {

        try {
            System.out.println("Proporcione el número de la opción a elegir.");
            int eleccion = Integer.parseInt(lectura.nextLine());
            // System.out.println(eleccion);
            if ((eleccion == 0) || (eleccion == 1) || (eleccion == 2) || (eleccion == 3) || (eleccion == 4)
                    || (eleccion == 5)) {
                return eleccion;
            } else {

                throw new ErrorOpcion("Entrada fuera del margen de las opciones");
            }

        } catch (NumberFormatException e) {
            System.out.println("La entrada ingresada no coincide con las opciones.");
            throw new NumberFormatException("Error por tipo de dato");
        } catch (ErrorOpcion e) {
            System.out.println("La opción ingresada no coincide con las opciones del menú");
            throw e;
        }
    }

    public String pedirDivisa(int opcion) {
        String divisa = "";

        switch (opcion) {
            case 1:
                System.out.println("Proporcione las siglas de la divisa a convertir: ");
                divisa = lectura.nextLine();
                break;
            case 2:
                System.out.println("Proporcione las siglas de la divisa a la que se decea convertir: ");
                divisa = lectura.nextLine();
                break;

        }

        divisa = divisa.toUpperCase();
        // System.out.println(divisa);

        return divisa;
    }

    public float pedirMonto() {
        try {
            System.out.println("Proporcione el monto a convertir: ");
            float divisaBaseFloat = Float.parseFloat(lectura.nextLine());
            return divisaBaseFloat;
        } catch (NumberFormatException e) {
            System.out.println("Error: lo ingresado no es un número");
            throw e;
        }

    }

    public void mostrarDivisas(MercadoDeDivisas divisas) {
        System.out.println("|---------------| Lista de Divisas |---------------|");

        for (List<String> lista : divisas.supported_codes()) {
            System.out.println("     - " + lista.get(1) + " (" + lista.get(0) + ")");
        }
    }

    public void mensajeDatosInsuficientes() {
        System.out.println(
                "Esta opción no esta disponible actualmente por falta de datos, proporcione las divizas y el monto a convertir.");
    }

    public void LimpiarPantalla() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            throw new RuntimeException("Error en proceso de limpieza");
        }
    }

    public void peticionSig() {
        System.out.println("--Enter para seguir--");
    }

    public void esperaYLimpieza() {
        this.peticionSig();
        this.espera();
        this.LimpiarPantalla();
    }

    public void cerrarFlujo() {
        lectura.close();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println();
    }

}

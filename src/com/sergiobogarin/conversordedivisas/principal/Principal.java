package com.sergiobogarin.conversordedivisas.principal;

//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;

import com.sergiobogarin.conversordedivisas.calculos.Monedero;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorCodigoDeDivisaIncorrecta;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorInactiveAccount;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorInvalidKey;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorMalformedRequest;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorOpcion;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorQuotaReached;
import com.sergiobogarin.conversordedivisas.io.ConsultorApi;
import com.sergiobogarin.conversordedivisas.io.InformeErrores;
import com.sergiobogarin.conversordedivisas.io.Interprete;
import com.sergiobogarin.conversordedivisas.modelos.EquivalenciaDeDivisa;
import com.sergiobogarin.conversordedivisas.modelos.MercadoDeDivisas;

import java.lang.NumberFormatException;

public class Principal {
    public static void main(String[] args) throws Exception {

        int peticion = 1;
        // float montoFloat = 0f;
        String montoString = "---";
        String siglasDivisaBase = "---";
        String siglasDivisaConvercion = "---";
        Interprete interpretador = new Interprete();
        ConsultorApi consulta = new ConsultorApi();
        Monedero monedero = new Monedero();
        MercadoDeDivisas divisas = null;
        EquivalenciaDeDivisa equivalencia = null;
        InformeErrores redactor = new InformeErrores();

        try {
            while (peticion != 0) {
                try {

                    interpretador.imprimirMenu(siglasDivisaBase, siglasDivisaConvercion, montoString);
                    peticion = interpretador.pedirOpcion();
                    interpretador.LimpiarPantalla();
                    switch (peticion) {
                        case 1:
                            interpretador.LimpiarPantalla();
                            // System.out.println("entrada 1: lista de divisas.");
                            divisas = consulta.consultarDivisas();
                            interpretador.mostrarDivisas(divisas);

                            break;
                        case 2:
                            siglasDivisaBase = interpretador.pedirDivisa(1);
                            interpretador.LimpiarPantalla();
                            break;
                        case 3:
                            siglasDivisaConvercion = interpretador.pedirDivisa(2);
                            interpretador.LimpiarPantalla();
                            break;
                        case 4:

                            montoString = monedero.tratamientoNumeroCuatroDecimales(interpretador.pedirMonto());

                            interpretador.LimpiarPantalla();
                            break;
                        case 5:
                            if (siglasDivisaBase.equals("---") || siglasDivisaConvercion.equals("---")
                                    || montoString.equals("---")) {
                                interpretador.mensajeDatosInsuficientes();
                                interpretador.espera();
                                interpretador.LimpiarPantalla();
                            } else {

                                equivalencia = consulta.consultarCambio(siglasDivisaBase, siglasDivisaConvercion);

                                interpretador.imprimirMenu(siglasDivisaBase, siglasDivisaBase, montoString,
                                        monedero.convertirDivisa(montoString, equivalencia.conversion_rate()),
                                        equivalencia.time_last_update_utc());
                                interpretador.espera();
                                interpretador.LimpiarPantalla();
                            }

                            break;
                    }
                } catch (NumberFormatException e) {
                    interpretador.mostrarMensaje(e.getMessage());
                    redactor.guardarError(e.toString());
                    interpretador.esperaYLimpieza();
                } catch (ErrorOpcion e) {
                    interpretador.mostrarMensaje(e.getMessage());
                    redactor.guardarError(e.toString());
                    interpretador.esperaYLimpieza();
                } catch (ErrorCodigoDeDivisaIncorrecta | ErrorInactiveAccount | ErrorInvalidKey | ErrorMalformedRequest
                        | ErrorQuotaReached e) {
                    interpretador.mostrarMensaje(e.getMessage());
                    redactor.guardarError(e.toString());
                    interpretador.esperaYLimpieza();
                } catch (NullPointerException e) {
                    interpretador.mostrarMensaje(e.getMessage());
                    redactor.guardarError(e.toString());
                    interpretador.esperaYLimpieza();
                } catch (RuntimeException e) {
                    interpretador.mostrarMensaje(e.getMessage());
                    redactor.guardarError(e.toString());
                    interpretador.esperaYLimpieza();
                } catch (Exception e) {
                    interpretador.mostrarMensaje(e.getMessage());
                    redactor.guardarError(e.toString());
                    interpretador.esperaYLimpieza();
                }
            }
        } catch (Exception e) {
            System.out.println("\n Error desconocido\n" + e.toString());
            redactor.guardarError(e.toString());
            interpretador.esperaYLimpieza();
        } finally {
            interpretador.cerrarFlujo();
            // System.out.println("Esto si pasa");
        }

        System.out.println("Programa terminado");

    }
}

// mejoras: crear en funcion la seccion de manejo de errores.
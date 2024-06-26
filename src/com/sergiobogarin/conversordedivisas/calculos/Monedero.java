package com.sergiobogarin.conversordedivisas.calculos;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Monedero {

    public String tratamientoNumeroCuatroDecimales(float valor) {

        DecimalFormat df = new DecimalFormat("#.####");
        String valorString = df.format(valor);
        // System.out.println(valorString);
        return valorString;
    }

    public String tratamientoNumeroDosDecimales(float valor) {

        DecimalFormat df = new DecimalFormat("#.##");
        String valorString = df.format(valor);
        // System.out.println(valorString);
        return valorString;
    }

    public String convertirDivisa(String divisaBase, String factorDeCovercion) {
        BigDecimal bdDivisaBase = new BigDecimal(divisaBase);
        BigDecimal bdFactConvercion = new BigDecimal(factorDeCovercion);
        // System.out.print(divisaBase + " x " + factorDeCovercion + " = ");

        return String.valueOf(bdDivisaBase.multiply(bdFactConvercion));
    }
}

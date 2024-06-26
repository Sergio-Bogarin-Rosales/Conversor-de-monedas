package com.sergiobogarin.conversordedivisas.io;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import java.util.HashMap;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorCodigoDeDivisaIncorrecta;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorInactiveAccount;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorInvalidKey;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorMalformedRequest;
import com.sergiobogarin.conversordedivisas.exceptions.ErrorQuotaReached;
import com.sergiobogarin.conversordedivisas.modelos.EquivalenciaDeDivisa;
import com.sergiobogarin.conversordedivisas.modelos.MercadoDeDivisas;

//import ErrorCodigoDeDivisaIncorrecta;

public class ConsultorApi {

    public EquivalenciaDeDivisa consultarCambio(String divisaBase, String divisaDestino) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/83de7176a4eb4221266e999d/pair/" + divisaBase
                + "/" + divisaDestino);
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonobj = new Gson().fromJson(response.body(), JsonObject.class);

            if ("success".equals(jsonobj.get("result").getAsString())) {

                return new Gson().fromJson(response.body(), EquivalenciaDeDivisa.class);

            } else if ("error".equals(jsonobj.get("result").getAsString())) {
                // System.out.println(jsonobj.get("error-type").getAsString()); //test
                if (jsonobj.get("error-type").getAsString().equals("unsupported-code")) {

                    throw new ErrorCodigoDeDivisaIncorrecta(
                            "Error unsupported-code: La sigla de alguna de las divisas seleccionadas se escribio erroneamente.");

                } else if (jsonobj.get("error-type").getAsString().equals("malformed-request")) {

                    throw new ErrorMalformedRequest(
                            "Error malformed-request: La estructura de la peticion no esta bien estructurada.");

                } else if (jsonobj.get("error-type").getAsString().equals("invalid-key")) {

                    throw new ErrorInvalidKey(
                            "Error invalid-key: La crontraseña usada para la consulta es erronea, intente con una distinta.");

                } else if (jsonobj.get("error-type").getAsString().equals("inactive-account")) {

                    throw new ErrorInactiveAccount(
                            "Error inactive-account: la dirección de correo electrónico usada no se encuentra validada.");

                } else if (jsonobj.get("error-type").getAsString().equals("quota-reached")) {

                    throw new ErrorQuotaReached(
                            "Error quota-reached: su cuenta ha alcanzado la cantidad de solicitudes permitidas para su plan");

                }
            }

        } catch (ErrorCodigoDeDivisaIncorrecta | ErrorInactiveAccount | ErrorInvalidKey | ErrorMalformedRequest
                | ErrorQuotaReached e) {
            // System.out.println(e.getMessage());
            throw e;
        } catch (IOException | InterruptedException e) {
            // System.out.println("No se logro hacer la coneccion. #3 \n" + e);
            throw new RuntimeException("\"No se logro hacer la coneccion.  #3 \n\n" + e);
        }
        return null;
    }

    public MercadoDeDivisas consultarDivisas() {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/83de7176a4eb4221266e999d/codes");
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonobj = new Gson().fromJson(response.body(), JsonObject.class);
            // System.out.println(jsonobj.get("result").getAsString());

            if ("success".equals(jsonobj.get("result").getAsString())) {

                return new Gson().fromJson(response.body(), MercadoDeDivisas.class);

            } else if ("error".equals(jsonobj.get("result").getAsString())) {
                System.out.println(jsonobj.get("error-type").getAsString());
                if (jsonobj.get("error-type").getAsString().equals("invalid-key")) {

                    throw new ErrorInvalidKey(
                            "Error invalid-key: La crontraseña usada para la consulta es erronea, intente con una distinta.");

                } else if (jsonobj.get("error-type").getAsString().equals("inactive-account")) {

                    throw new ErrorInactiveAccount(
                            "Error inactive-account: la dirección de correo electrónico usada no se encuentra validada.");

                } else if (jsonobj.get("error-type").getAsString().equals("quota-reached")) {

                    throw new ErrorQuotaReached(
                            "Error quota-reached: su cuenta ha alcanzado la cantidad de solicitudes permitidas para su plan");
                }
            }

        } catch (ErrorInactiveAccount | ErrorInvalidKey | ErrorQuotaReached e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("No se logro hacer la coneccion.  #2 \n" + e);
        } catch (Exception e) {
            throw new RuntimeException(" Error de desconocido" + e);
        }
        return null;
    }

}

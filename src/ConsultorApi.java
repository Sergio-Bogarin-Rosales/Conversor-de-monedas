import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.io.IOException;

import com.google.gson.Gson;

public class ConsultorApi {

    public EquivalenciaDeDivisa consultarCambio() {
        // Seccion comun: Creacion de Objeto url, httpcliente, httprequest
        // URI direccion =
        // URI.create("https://v6.exchangerate-api.com/v6/83de7176a4eb4221266e999d/latest/USD");
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/83de7176a4eb4221266e999d//pair/USD/MXN");
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        // pide la respuesta de la API, la cual devolvera el equivalente de la divisa
        // base por la pedida.
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            // String json = response.body(); //reestructura a json
            // System.out.println(json);
            return new Gson().fromJson(response.body(), EquivalenciaDeDivisa.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("No se logro hacer la coneccion.  #1 \n" + e);
        }

    }

    public MercadoDeDivisas consultarDivisas() {
        // Seccion comun: Creacion de Objeto url, httpcliente, httprequest
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/83de7176a4eb4221266e999d/codes");
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            // String json = response.body();
            // System.out.println(json);
            return new Gson().fromJson(response.body(), MercadoDeDivisas.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("No se logro hacer la coneccion.  #2 \n" + e);
        }
    }

}

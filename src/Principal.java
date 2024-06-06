public class Principal {
    public static void main(String[] args) throws Exception {
        ConsultorApi consulta = new ConsultorApi();
        EquivalenciaDeDivisa equivalencia = consulta.consultarCambio();
        System.out.println(equivalencia + "\n");
        MercadoDeDivisas divisas = consulta.consultarDivisas();
        System.out.println(divisas + "\n");
        System.out.println(divisas.supported_codes());

        // Map<String, String> mercadoDivisas = divisas.supported_codes();
        // Map<String, String> manejoDeDivisas = divisas.supported_codes();

        System.out.println("se termino bien el programa");

    }
}

package com.sergiobogarin.conversordedivisas.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InformeErrores {

    File fichero = null;
    FileWriter escribir = null;

    public void guardarError(String error) throws IOException {
        try {
            this.fichero = new File("Registro-de-errores.txt");
            if (this.fichero.exists()) {
                // System.out.println("entrada 2 if"); test
                this.escribir = new FileWriter(this.fichero, true);
            } else {
                // System.out.println("entrada 2 else"); test
                this.escribir = new FileWriter(this.fichero, false);
            }
            this.escribir.write(error + "\n");

        } catch (IOException e) {
            System.out.println("Error al aperturar archivo, para registro de errores.");
        } catch (Exception e) {
            System.out.println("Error desconocido encontrado a la hora de registro de errores.");
        } finally {
            try {
                if (null != this.escribir) {
                    this.escribir.close();
                }
            } catch (Exception e) {
                System.out.println("Error en cierre de archivo.");
            }

        }
    }
}

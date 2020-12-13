package Dominio.Contrasenia.Core;

import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChequearContraseniaComun implements IValidacion {
    private static List<String> contraseniaComun;
    private static String archivoContraseniasComunes="10000_commonPasswords.txt"; //en local: "10000_commonPasswords.txt"

    // Vuela cuando sea base de datos
    static {
        try {
            contraseniaComun = obtenerContraseniasComunes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void validar(String contrasenia) throws ExcepcionContraseniaComun {

        boolean contraseniaEncontrada = contraseniaComun.stream().anyMatch(commonPassword -> commonPassword.equals(contrasenia));

        if(contraseniaEncontrada) throw new ExcepcionContraseniaComun();
    }

    //Vamos a manejar el geteo de las passwords con un archivo
    private static List<String> obtenerContraseniasComunes() throws IOException {
        String line;
        List<String> contraseniasComunes = new ArrayList();

        File file = new File(archivoContraseniasComunes);

        BufferedReader buffer = new BufferedReader(new FileReader(file));

        while ((line = buffer.readLine()) != null)
            contraseniasComunes.add(line);

        return contraseniasComunes;
    }
}

package Contrasenia.Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Contrasenia.Excepciones.*;

public class ChequearContraseniaComun implements IValidacion {
    private static List<String> contraseniaComun;

    // Vuela cuando sea base de datos
    static {
        try {
            contraseniaComun = obtenerContraseniasComunes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validar(String contrasenia) throws ExcepcionContraseniaComun {

        boolean contraseniaEncontrada = contraseniaComun.stream().anyMatch(commonPassword -> commonPassword.equals(contrasenia));

        if(contraseniaEncontrada) throw new ExcepcionContraseniaComun();
    }

    //Vamos a manejar el geteo de las passwords con un archivo
    private static List<String> obtenerContraseniasComunes() throws IOException {
        String line;
        List<String> contraseniasComunes = new ArrayList();

        File file = new File("10000_commonPasswords.txt");

        BufferedReader buffer = new BufferedReader(new FileReader(file));

        while ((line = buffer.readLine()) != null)
            contraseniasComunes.add(line);

        return contraseniasComunes;
    }
}

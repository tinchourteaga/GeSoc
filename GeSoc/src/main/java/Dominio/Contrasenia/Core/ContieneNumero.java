package Dominio.Contrasenia.Core;

import Dominio.Contrasenia.Excepciones.*;
import Dominio.Convertidor.ConvertidorString;
import java.util.List;

public class ContieneNumero implements IValidacion {
    @Override
    public void validar(String contrasenia) throws ExcepcionNumero {
        List<Character> caracteresContrasenia = ConvertidorString.hacerListaDeStrings(contrasenia);

        boolean contieneNumero = caracteresContrasenia.stream().anyMatch(ConvertidorString::esNumero);

        if(!contieneNumero) throw new ExcepcionNumero();
    }
}

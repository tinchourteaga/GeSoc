package Dominio.Contrasenia.Core;

import Dominio.Contrasenia.Excepciones.*;
import Dominio.Convertidor.ConvertidorString;
import java.util.List;

public class ContieneCaracterEspecial implements IValidacion {
    @Override
    public void validar(String contrasenia) throws ExcepcionCaracterEspecial {
        List<Character> caracteresContrasenia = ConvertidorString.hacerListaDeStrings(contrasenia);

        boolean contieneCaracterEspecial = caracteresContrasenia.stream().anyMatch(ConvertidorString::esEspecial);

        if(!contieneCaracterEspecial) throw new ExcepcionCaracterEspecial();

    }
}

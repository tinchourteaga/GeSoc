package Dominio.Contrasenia.Core;

import Dominio.Contrasenia.Excepciones.*;

public class ChequearLongitudContrasenia implements IValidacion {


    public static void validar(String contrasenia) throws ExcepcionLongitud {
        boolean longitudSuficiente = contrasenia.length() >= 8;

        if(!longitudSuficiente) throw new ExcepcionLongitud();
    }
}

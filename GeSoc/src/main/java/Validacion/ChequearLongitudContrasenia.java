package Validacion;

import Validacion.Excepciones.*;

public class ChequearLongitudContrasenia implements IValidacion {

    @Override
    public void validar(String contrasenia) throws ExcepcionLongitud {
        boolean longitudSuficiente = contrasenia.length() >= 8;

        if(!longitudSuficiente) throw new ExcepcionLongitud();
    }
}

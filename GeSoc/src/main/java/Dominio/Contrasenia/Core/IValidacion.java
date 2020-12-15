package Dominio.Contrasenia.Core;

import Dominio.Contrasenia.Excepciones.*;
import java.io.IOException;

public interface IValidacion {

    static void validar(String contrasenia) throws ExcepcionContraseniaComun, IOException, ExcepcionLongitud, ExcepcionNumero, ExcepcionCaracterEspecial{

    }
}

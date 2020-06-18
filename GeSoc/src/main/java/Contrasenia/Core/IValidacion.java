package Contrasenia.Core;

import Contrasenia.Excepciones.*;
import java.io.IOException;

public interface IValidacion {

    void validar(String contrasenia) throws ExcepcionContraseniaComun, IOException, ExcepcionLongitud, ExcepcionNumero, ExcepcionCaracterEspecial;
}

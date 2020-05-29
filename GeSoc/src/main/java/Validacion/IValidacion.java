package Validacion;

import Validacion.Excepciones.*;
import java.io.IOException;

public interface IValidacion {

    void validar(String contrasenia) throws ExcepcionContraseniaComun, IOException, ExcepcionLongitud, ExcepcionNumero, ExcepcionCaracterEspecial;
}

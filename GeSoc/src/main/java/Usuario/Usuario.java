package Usuario;

import java.io.IOException;
import Validacion.*;
import Validacion.Excepciones.*;

public class Usuario {

    private String rol;
    private String nombre;
    private String contrasenia;

    private Usuario(String rol, String nombre, String contrasenia) throws ExcepcionCaracterEspecial, ExcepcionContraseniaComun, ExcepcionNumero, ExcepcionLongitud, IOException {
        this.rol = rol;
        this.nombre = nombre;
        ValidadorDeContrasenia.validarContrasenia(contrasenia);
        this.contrasenia = contrasenia;
    }
}
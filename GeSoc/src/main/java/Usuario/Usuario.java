package Usuario;

import java.io.IOException;
import java.util.List;
import Contrasenia.Core.ValidadorDeContrasenia;
import Rol.Rol;
import Rol.Exepciones.ContraseniasDistintasException;
import Contrasenia.Excepciones.*;

public class Usuario {

    private List<Rol> roles;
    private String nombre;
    private String contrasenia;

    public Usuario(List<Rol> roles, String nombre, String contrasenia) throws ExcepcionCaracterEspecial, ExcepcionContraseniaComun, ExcepcionNumero, ExcepcionLongitud, IOException {
        this.roles = roles;
        this.nombre = nombre;
        ValidadorDeContrasenia.validarContrasenia(contrasenia);
        this.contrasenia = contrasenia;
    }


    public String getNombre() {
        return nombre;
    }
    public List<Rol> getRoles() {
        return roles;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    private void setContrasenia (String contraseniaNueva) throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        ValidadorDeContrasenia.validarContrasenia(contraseniaNueva);
        this.contrasenia = contraseniaNueva;
        }

    public void cambiarContrasenia(String passwordAnterior, String passwordNueva) throws ContraseniasDistintasException, IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        if (this.contrasenia.equals(passwordAnterior)){
            this.setContrasenia(passwordNueva);
        }else{
            throw new ContraseniasDistintasException();
        }
    }

}


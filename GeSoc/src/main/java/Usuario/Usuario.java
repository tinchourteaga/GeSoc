package Usuario;

import java.io.IOException;
import java.util.List;

import Contrasenia.Core.ValidadorDeContrasenia;
import Egreso.Core.Egreso;
import Rol.Acciones.Accion;
import Rol.Acciones.RealizarCompra;
import Rol.Rol;
import Rol.Exepciones.ContraseniasDistintasException;
import Rol.Exepciones.NoTengoPermisosException;
import Rol.Exepciones.NoTengoPermisosExceptionDeCompra;
import Contrasenia.Excepciones.*;

public class Usuario {

    private List<Rol> roles;
    private String nombre;
    private String contrasenia;

    private Usuario(List<Rol> roles, String nombre, String contrasenia) throws ExcepcionCaracterEspecial, ExcepcionContraseniaComun, ExcepcionNumero, ExcepcionLongitud, IOException {
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

    public void realizarOperacionCompra(Egreso operacion) throws NoTengoPermisosException, NoTengoPermisosExceptionDeCompra {
        Accion unaAccion = new RealizarCompra(operacion,this);
        roles.forEach(unRol -> {
            try {
                unRol.tengoPermisosPara(unaAccion);
            } catch (NoTengoPermisosException e) {
                e.printStackTrace();
            }
        });
        unaAccion.realizar(this);
    }

    public void setContrasenia (String contraseniaNueva){
            contrasenia = contraseniaNueva;
        }


    private void cambiarContrasenia(String passwordAnterior, String passwordNueva) throws ContraseniasDistintasException {
        if (this.contrasenia.equals(passwordAnterior)){
            this.contrasenia=passwordNueva;
        }else{

            throw new ContraseniasDistintasException();
        }
    }

}


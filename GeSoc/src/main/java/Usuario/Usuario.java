package Usuario;

import java.io.IOException;
import java.util.List;

import Operacion.Core.Operacion;
import Usuario.Exepciones.ContraseniasDistintasException;
import Usuario.Exepciones.NoTengoPermisosException;
import Usuario.Exepciones.NoTengoPermisosExceptionDeCompra;
import Usuario.Exepciones.NoTengoPermisosExceptionDeRevisarCompra;
import Validacion.*;
import Validacion.Excepciones.*;

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

    public void realizarOperacionCompra(Operacion operacion) throws NoTengoPermisosException, NoTengoPermisosExceptionDeCompra {
        //no se si esto esta bien, lo dejo hasta estar seguro de que compra y egreso son lo mismo
        if (roles.stream().allMatch(rol->rol.tengoPermisosPara(Accion.REALIZAR_COMPRA))) {
            operacion.realizar();
        } else {
            throw new NoTengoPermisosExceptionDeCompra(this,operacion);
        }
    }

    public void revisarCompra(Operacion compra) throws  NoTengoPermisosExceptionDeRevisarCompra {
        if (roles.stream().allMatch(rol->rol.tengoPermisosPara(Accion.REVISAR_COMPRA))) {
            compra.revisar(this);
        } else {
            throw new NoTengoPermisosExceptionDeRevisarCompra(this,compra);
        }
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


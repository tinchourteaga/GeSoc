package Usuario;

import java.io.IOException;

import Operacion.Operacion;
import Usuario.Exepciones.ContraseniasDistintasException;
import Usuario.Exepciones.NoTengoPermisosException;
import Usuario.Exepciones.NoTengoPermisosExceptionDeCompra;
import Usuario.Exepciones.NoTengoPermisosExceptionDeRevisarCompra;
import Validacion.*;
import Validacion.Excepciones.*;

public class Usuario {

    private Rol rol;
    private String nombre;
    private String contrasenia;

    private Usuario(Rol rol, String nombre, String contrasenia) throws ExcepcionCaracterEspecial, ExcepcionContraseniaComun, ExcepcionNumero, ExcepcionLongitud, IOException {
        this.rol = rol;
        this.nombre = nombre;
        ValidadorDeContrasenia.validarContrasenia(contrasenia);
        this.contrasenia = contrasenia;
    }


    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void realizarOperacionCompra(Operacion operacion) throws NoTengoPermisosException, NoTengoPermisosExceptionDeCompra {
        //no se si esto esta bien, lo dejo hasta estar seguro de que compra y egreso son lo mismo
        if (rol.tengoPermisosPara(Accion.REALIZAR_COMPRA)) {
            operacion.realizar();
        } else {
            throw new NoTengoPermisosExceptionDeCompra(this,operacion);
        }
    }

    public void revisarCompra(Operacion compra) throws  NoTengoPermisosExceptionDeRevisarCompra {
        if (rol.tengoPermisosPara(Accion.REVISAR_COMPRA)) {
            compra.revisar(this);
        } else {
            throw new NoTengoPermisosExceptionDeRevisarCompra(this,compra);
        }
    }


        public void setContrasenia (String contraseniaNueva){
            contrasenia = contraseniaNueva;
        }


    private void cambiarContrasenia(String passwordAnterior, String passwordNueva){
        if (this.contrasenia.equals(passwordAnterior)){
            this.contrasenia=passwordNueva;
        }else{

            throw new ContraseniasDistintasException();
        }
    }

}


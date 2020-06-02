package Usuario;

import java.io.IOException;

import Operacion.Operacion;
import Usuario.Exepciones.NoTengoPermisosException;
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
    public Rol getRol(){
        return rol;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void realizarOperacion(Operacion operacion) throws NoTengoPermisosException {
        //supongo que la crea
        if(rol.tengoPermisosPara(Accion.REALIZAR_OPERACION)){
        operacion.realizar();
        }else{
            throw new NoTengoPermisosException();
            //podriamos ser mas especificos para la prox
            //y hacer excepciones por cada enum
        }
    }

    public void setContrasenia(String contraseniaNueva){
    contrasenia=contraseniaNueva;
    }
}

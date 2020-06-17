package Rol.Exepciones;

import Operacion.Core.Operacion;
import Usuario.Usuario;

public class NoTengoPermisosExceptionDeCompra extends Throwable {
    public NoTengoPermisosExceptionDeCompra(Usuario usuario, Operacion compra) {
        super("El usuario"+usuario.getNombre() +" no tiene los permisos para realizar la compra"+ compra.toString());
    }
}

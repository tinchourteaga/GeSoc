package Rol.Exepciones;

import Egreso.Core.Egreso;
import Usuario.Usuario;

public class NoTengoPermisosExceptionDeCompra extends Throwable {
    public NoTengoPermisosExceptionDeCompra(Usuario usuario, Egreso compra) {
        super("El usuario"+usuario.getNombre() +" no tiene los permisos para realizar la compra"+ compra.toString());
    }
}

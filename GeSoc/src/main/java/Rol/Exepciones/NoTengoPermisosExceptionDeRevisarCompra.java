package Rol.Exepciones;

import Egreso.Core.Egreso;
import Usuario.Usuario;

public class NoTengoPermisosExceptionDeRevisarCompra extends Exception {
    public NoTengoPermisosExceptionDeRevisarCompra(Usuario usuario, Egreso unaOperacion) {
        super("El usuario"+usuario.getNombre() +" no tiene los permisos para revisar la compra"+ unaOperacion.toString());
    }
}



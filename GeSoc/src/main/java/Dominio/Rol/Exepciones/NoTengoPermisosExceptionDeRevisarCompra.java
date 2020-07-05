package Dominio.Rol.Exepciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Usuario.Usuario;

public class NoTengoPermisosExceptionDeRevisarCompra extends Exception {
    public NoTengoPermisosExceptionDeRevisarCompra(Usuario usuario, Egreso unaOperacion) {
        super("El usuario"+usuario.getNombre() +" no tiene los permisos para revisar la compra"+ unaOperacion.toString());
    }
}



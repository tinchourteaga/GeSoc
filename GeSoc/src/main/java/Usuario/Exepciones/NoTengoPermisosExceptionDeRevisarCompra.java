package Usuario.Exepciones;

import Operacion.Operacion;
import Usuario.Usuario;

public class NoTengoPermisosExceptionDeRevisarCompra extends Exception {
    public NoTengoPermisosExceptionDeRevisarCompra(Usuario usuario, Operacion unaOperacion) {
        super("El usuario"+usuario.getNombre() +" no tiene los permisos para revisar la compra"+ unaOperacion.toString());
    }
}



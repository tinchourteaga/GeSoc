package Dominio.Rol.Exepciones;

public class NoTengoPermisosException extends Exception {
    public NoTengoPermisosException(){
            super("El usuario no tiene los permisos para realizar tal accion");
    }
}

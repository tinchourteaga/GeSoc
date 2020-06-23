package Rol.Acciones;

import Rol.Rol;
import Usuario.Usuario;

public class AsignarRol implements Accion{

    Rol rolAAsignar;
    Usuario usuarioQueLoNecesita;
    public AsignarRol(Rol rol, Usuario usuario) {
        this.rolAAsignar=rol;
        this.usuarioQueLoNecesita=usuario;
    }

    @Override
    public void realizar() {
        usuarioQueLoNecesita.getRoles().add(rolAAsignar);
    }
}

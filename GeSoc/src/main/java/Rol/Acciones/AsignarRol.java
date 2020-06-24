package Rol.Acciones;

import Rol.Rol;
import Usuario.Usuario;

public class AsignarRol implements Accion{

    private Rol rolAAsignar;
    private Usuario usuarioQueLoNecesita;

    @Override
    public void realizar() {
        usuarioQueLoNecesita.getRoles().add(rolAAsignar);
    }

    public AsignarRol(Rol rol, Usuario usuario) {
        this.rolAAsignar=rol;
        this.usuarioQueLoNecesita=usuario;
    }


}

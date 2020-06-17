package Rol.Acciones;

import Operacion.Core.Operacion;
import Rol.Rol;
import Usuario.Usuario;

import java.util.List;

public class RealizarCompra implements Accion{
    Operacion operacion;

    public RealizarCompra(Operacion operacion, Usuario unUsuario) {
        this.operacion = operacion;
        List<Rol> roles = unUsuario.getRoles();
        roles.forEach(unRol -> unRol.acciones.add(this));
    }

    @Override
    public void realizar(Usuario usuario) {

    }
}

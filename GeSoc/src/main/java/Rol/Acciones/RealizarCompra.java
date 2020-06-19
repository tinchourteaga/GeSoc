package Rol.Acciones;

import Egreso.Core.Egreso;
import Rol.Rol;
import Usuario.Usuario;

import java.util.List;

public class RealizarCompra implements Accion{
    Egreso operacion;

    public RealizarCompra(Egreso operacion, Usuario unUsuario) {
        this.operacion = operacion;
        List<Rol> roles = unUsuario.getRoles();
        roles.forEach(unRol -> unRol.acciones.add(this));
    }

    @Override
    public void realizar(Usuario usuario) {

    }
}

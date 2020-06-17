package Rol.Acciones;

import Operacion.Core.Operacion;
import Usuario.Usuario;

public class RealizarCompra implements Accion{
    Operacion operacion;

    public RealizarCompra(Operacion operacion) {
        this.operacion = operacion;
    }

    @Override
    public void realizar(Usuario usuario) {

    }
}

package Rol.Acciones;

import Egreso.Core.Egreso;

public class ValidarCompra implements Accion{
    private Egreso operacion;

    @Override
    public void realizar() {
        operacion.validar();
    }

    public ValidarCompra(Egreso operacion) {this.operacion = operacion;}
}

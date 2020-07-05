package Dominio.Rol.Acciones;

import Dominio.Egreso.Core.Egreso;

public class ValidarCompra implements Accion{
    private Egreso operacion;

    @Override
    public void realizar() {
        operacion.validar();
    }

    public ValidarCompra(Egreso operacion) {this.operacion = operacion;}
}

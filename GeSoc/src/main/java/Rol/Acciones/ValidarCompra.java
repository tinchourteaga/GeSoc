package Rol.Acciones;

import Egreso.Core.Egreso;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Rol.Rol;
import Usuario.Usuario;

import java.util.List;

public class ValidarCompra implements Accion{
    Egreso operacion;

    public ValidarCompra(Egreso operacion) {
        this.operacion = operacion;}


    @Override
    public void realizar() throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {
        operacion.validar();

    }
}

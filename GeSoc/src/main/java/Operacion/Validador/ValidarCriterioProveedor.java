package Operacion.Validador;

import Operacion.Core.Operacion;
import Operacion.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Operacion.Validador.Excepciones.NoCumpleValidacionException;

public class ValidarCriterioProveedor implements ValidacionOperacion {

    @Override
    public void validar(Operacion operacion) throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {
        operacion.getCriterio().validar(operacion);
    }
}

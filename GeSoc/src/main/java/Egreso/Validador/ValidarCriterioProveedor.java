package Egreso.Validador;

import Egreso.Core.Egreso;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;

public class ValidarCriterioProveedor implements ValidacionOperacion {

    @Override
    public void validar(Egreso operacion) throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {
        operacion.getCriterio().validar(operacion);
    }
}

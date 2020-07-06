package Dominio.Egreso.Validador.Validaciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;

public class ValidacionCriterioProveedor implements ValidacionOperacion {

    @Override
    public void validar(Egreso operacion) throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {
        operacion.getCriterio().validar(operacion);
    }
}

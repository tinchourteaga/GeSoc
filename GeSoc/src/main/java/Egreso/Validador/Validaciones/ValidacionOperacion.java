package Egreso.Validador.Validaciones;

import Egreso.Core.Egreso;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;

public interface ValidacionOperacion {
    void validar(Egreso unaOperacion) throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException;
}

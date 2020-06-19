package Operacion.Validador;

import Operacion.Core.Operacion;
import Operacion.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Operacion.Validador.Excepciones.NoCumpleValidacionException;

public interface ValidacionOperacion {
    public void validar(Operacion unaOperacion) throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException;
}

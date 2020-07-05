package Dominio.Egreso.Validador.Validaciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;

public interface ValidacionOperacion {
    void validar(Egreso unaOperacion) throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException;
}

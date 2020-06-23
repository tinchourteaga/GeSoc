package Rol.Acciones;

import Egreso.Core.CriteriosDeCategorizacion.Jerarquia;
import Egreso.Core.Egreso;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;

public class AplicarJerarquia implements Accion {
    private Jerarquia jerarquiaAAplicar;
    private Egreso egresoAQuienAplco;

    @Override
    public void realizar() throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {
        jerarquiaAAplicar.aplicar(egresoAQuienAplco);
    }
}

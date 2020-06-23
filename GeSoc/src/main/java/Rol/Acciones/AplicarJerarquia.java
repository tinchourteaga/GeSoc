package Rol.Acciones;

import Egreso.Core.CriteriosDeCategorizacion.Jerarquia;
import Egreso.Core.Egreso;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;

public class AplicarJerarquia implements Accion {
    private Jerarquia jerarquiaAAplicar;
    private Egreso egresoAQuienAplco;

    public AplicarJerarquia(Jerarquia jerarquiaAAplicar, Egreso egresoAQuienAplco) {
        this.jerarquiaAAplicar = jerarquiaAAplicar;
        this.egresoAQuienAplco = egresoAQuienAplco;
    }

    @Override
    public void realizar() throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {
        jerarquiaAAplicar.aplicar(egresoAQuienAplco);
    }
}

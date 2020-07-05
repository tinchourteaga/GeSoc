package Dominio.Rol.Acciones;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Jerarquia;
import Dominio.Egreso.Core.Egreso;

public class AplicarJerarquia implements Accion {
    private Jerarquia jerarquiaAAplicar;
    private Egreso egresoAQuienAplco;

    public AplicarJerarquia(Jerarquia jerarquiaAAplicar, Egreso egresoAQuienAplco) {
        this.jerarquiaAAplicar = jerarquiaAAplicar;
        this.egresoAQuienAplco = egresoAQuienAplco;
    }

    @Override
    public void realizar(){
        jerarquiaAAplicar.aplicar(egresoAQuienAplco);
    }
}

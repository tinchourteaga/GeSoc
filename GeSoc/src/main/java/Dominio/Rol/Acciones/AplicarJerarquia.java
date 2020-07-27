package Dominio.Rol.Acciones;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.Egreso;

public class AplicarJerarquia implements Accion {
    private Criterio criterio;
    private Egreso egresoAQuienAplico;

    public AplicarJerarquia(Criterio criterio, Egreso egresoAQuienAplico) {
        this.criterio = criterio;
        this.egresoAQuienAplico = egresoAQuienAplico;
    }

    @Override
    public void realizar(){
        criterio.aplicar(egresoAQuienAplico);
    }
}

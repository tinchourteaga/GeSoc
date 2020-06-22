package Egreso.Core.CriteriosDeCategorizacion;

import Egreso.Core.Egreso;

public class JerarquiaHoja implements Jerarquia {
    Criterio criterioCorte;

    @Override
    public void aplicar(Egreso unEgreso) {
        criterioCorte.aplicar(unEgreso);
    }
    public JerarquiaHoja(Criterio unCriterio){
        this.criterioCorte=unCriterio;
    }
}

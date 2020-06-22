package Egreso.Core.CriteriosDeCategorizacion;

import Egreso.Core.Egreso;

public class JerarquiaRecursiva implements Jerarquia{
    private Criterio criterioPadre;
    private Jerarquia jerarquiaHijo;

    @Override
    public void aplicar(Egreso unEgreso) {
        jerarquiaHijo.aplicar(unEgreso);
        criterioPadre.aplicar(unEgreso);
    }
    public JerarquiaRecursiva(Criterio unCriterio,Jerarquia unaJerarquia){
        this.criterioPadre=unCriterio;
        this.jerarquiaHijo=unaJerarquia;
    }
}

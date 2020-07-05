package Dominio.Egreso.Core.CriteriosDeCategorizacion;

import Dominio.Egreso.Core.Egreso;
import java.util.List;

public class Jerarquia {

    private Criterio criterio;
    private List<Jerarquia> hijos;

    public Jerarquia(Criterio criterio, List<Jerarquia> hijos) {
        this.criterio = criterio;
        this.hijos = hijos;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public List<Jerarquia> getHijos() {
        return hijos;
    }
    public void agregarHijo(Jerarquia unajerarquia){
        hijos.add(unajerarquia);
    }
    public void aplicar(Egreso compra){
        compra.asignarCriterioDeCategorizacion(criterio);
        hijos.forEach(hijo->hijo.aplicar(compra));
    }
}

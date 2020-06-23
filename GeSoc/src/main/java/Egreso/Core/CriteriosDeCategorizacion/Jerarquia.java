package Egreso.Core.CriteriosDeCategorizacion;

import Egreso.Core.Egreso;

import java.util.ArrayList;
import java.util.List;

public class Jerarquia {

    Criterio criterio;
    List<Jerarquia> hijos=new ArrayList<>();

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

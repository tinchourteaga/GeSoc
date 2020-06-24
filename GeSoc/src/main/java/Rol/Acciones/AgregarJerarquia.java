package Rol.Acciones;

import Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Egreso.Core.CriteriosDeCategorizacion.Jerarquia;
import Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AgregarJerarquia implements Accion {

    private Jerarquia jerarquiaAsociada;
    private Criterio criterioAsociado;

    @Override
    public void realizar() {
        List<Jerarquia> hijos=new ArrayList<>();
        hijos.add(jerarquiaAsociada);
        this.setJerarquiaAsociada(new Jerarquia(criterioAsociado,hijos));
        //habria que cambiar el aplicar jerarquia
    }

    //getters y setters
    public Jerarquia getJerarquiaAsociada() {
        return jerarquiaAsociada;
    }
    public void setJerarquiaAsociada(Jerarquia jerarquiaAsociada) {
        this.jerarquiaAsociada = jerarquiaAsociada;
    }
    public Criterio getCriterioAsociado() {
        return criterioAsociado;
    }
    public void setCriterioAsociado(Criterio criterioAsociado) {
        this.criterioAsociado = criterioAsociado;
    }

    //constructor
    public AgregarJerarquia(Criterio criterio, Jerarquia unaJerarquia) {

        this.criterioAsociado = criterio;
        this.jerarquiaAsociada = unaJerarquia;

    }




}

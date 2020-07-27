package Dominio.Rol.Acciones;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;

import java.util.ArrayList;
import java.util.List;

public class AgregarJerarquia implements Accion {

    private Criterio criterioHijo;
    private Criterio criterioPadre;

    //constructor
    public AgregarJerarquia(Criterio criterioPadre, Criterio criterioHijo) {

        this.criterioPadre = criterioPadre;
        this.criterioHijo = criterioHijo;

    }

    @Override
    public void realizar() {
        criterioPadre.getHijos().add(criterioHijo);
    }


    public Criterio getCriterioHijo() {
        return criterioHijo;
    }

    public void setCriterioHijo(Criterio criterioHijo) {
        this.criterioHijo = criterioHijo;
    }

    public Criterio getCriterioPadre() {
        return criterioPadre;
    }

    public void setCriterioPadre(Criterio criterioPadre) {
        this.criterioPadre = criterioPadre;
    }
}

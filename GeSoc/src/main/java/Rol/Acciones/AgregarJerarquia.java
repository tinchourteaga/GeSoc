package Rol.Acciones;

import Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Egreso.Core.CriteriosDeCategorizacion.JerarquiaRecursiva;
import Usuario.Usuario;

public class AgregarJerarquia implements Accion {

    JerarquiaRecursiva jerarquiaAsociada;
    Criterio criterioAsociado;
    @Override
    public void realizar(Usuario usuario) {
        this.setJerarquiaAsociada(new JerarquiaRecursiva(criterioAsociado,jerarquiaAsociada));
        //habria que cambiar el aplicar jerarquia
    }

    public JerarquiaRecursiva getJerarquiaAsociada() {
        return jerarquiaAsociada;
    }

    public void setJerarquiaAsociada(JerarquiaRecursiva jerarquiaAsociada) {
        this.jerarquiaAsociada = jerarquiaAsociada;
    }

    public Criterio getCriterioAsociado() {
        return criterioAsociado;
    }

    public void setCriterioAsociado(Criterio criterioAsociado) {
        this.criterioAsociado = criterioAsociado;
    }

    public AgregarJerarquia(Criterio criterio, JerarquiaRecursiva unaJerarquia){

        this.criterioAsociado=criterio;
        this.jerarquiaAsociada=unaJerarquia;

    }
}

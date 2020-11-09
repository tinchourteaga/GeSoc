package Dominio.Rol.Acciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Usuario.Usuario;

public class DarDeAltaRevisor implements Accion {
    private Usuario revisor;
    private Egreso egreso;

    public DarDeAltaRevisor(Usuario revisor, Egreso egreso) {
        this.revisor = revisor;
        this.egreso = egreso;
    }

    @Override
    public void realizar() {
        Revisor rolRevisor = new Revisor();
        revisor.setRol(rolRevisor);
        rolRevisor.agregarEgreso(egreso);
    }
}

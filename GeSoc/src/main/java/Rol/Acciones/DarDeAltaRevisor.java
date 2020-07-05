package Rol.Acciones;

import Egreso.Core.Egreso;
import Rol.RolRevisorCompra;
import Usuario.Usuario;

public class DarDeAltaRevisor implements Accion {
    private Usuario revisor;
    private Egreso egreso;

    public DarDeAltaRevisor(Usuario revisor, Egreso egreso) {
        this.revisor = revisor;
        this.egreso = egreso;
    }

    @Override
    public void realizar() {
        RolRevisorCompra rolRevisor= new RolRevisorCompra(egreso);
        revisor.getRoles().add(rolRevisor);

    }
}

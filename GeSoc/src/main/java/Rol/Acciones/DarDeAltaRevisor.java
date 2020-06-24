package Rol.Acciones;

import Egreso.Core.Egreso;
import Rol.Mensajero;
import Rol.RolRevisorCompra;
import Usuario.Usuario;

import java.util.Date;
import java.util.List;

public class DarDeAltaRevisor implements Accion {
    private Usuario revisor;
    private Egreso egreso;

    public DarDeAltaRevisor(Usuario revisor) {
        this.revisor = revisor;
    }

    @Override
    public void realizar() {
        RolRevisorCompra rolRevisor= new RolRevisorCompra(egreso);
        revisor.getRoles().add(rolRevisor);

    }
}

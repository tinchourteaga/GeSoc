package Rol.Acciones;

import Egreso.Core.Egreso;
import Rol.RolRevisorCompra;
import Usuario.Usuario;

public class DarDeAltaRevisor implements Accion {
    Usuario revisor;
    Egreso egreso;

    public DarDeAltaRevisor(Usuario revisor) {
        this.revisor = revisor;
    }

    @Override
    public void realizar(Usuario usuario) {

        RolRevisorCompra rolRevisor= new RolRevisorCompra(egreso);
        usuario.getRoles().add(rolRevisor);
    }
}

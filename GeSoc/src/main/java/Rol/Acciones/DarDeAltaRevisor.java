package Rol.Acciones;

import Usuario.Usuario;

public class DarDeAltaRevisor implements Accion {
    Usuario revisor;

    public DarDeAltaRevisor(Usuario revisor) {
        this.revisor = revisor;
    }

    @Override
    public void realizar(Usuario usuario) {

    }
}

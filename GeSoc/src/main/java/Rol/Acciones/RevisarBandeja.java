package Rol.Acciones;

import BandejaMensajes.BandejaMensajes;
import Usuario.Usuario;

public class RevisarBandeja implements Accion {
    BandejaMensajes bandejaAsociada;

    public RevisarBandeja(BandejaMensajes bandejaAsociada) {
        this.bandejaAsociada = bandejaAsociada;
    }

    @Override
    public void realizar(Usuario usuario) {

    }
}

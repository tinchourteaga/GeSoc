package Rol.Acciones;

import BandejaMensajes.BandejaMensajes;
import Usuario.Usuario;

public class RevisarBandeja implements Accion {
    private BandejaMensajes bandejaAsociada;

    public RevisarBandeja(BandejaMensajes bandejaAsociada) {
        this.bandejaAsociada = bandejaAsociada;
    }

    @Override
    public void realizar() {
        //esto probablemente toquemos mas en la entrega 5
    }

    public BandejaMensajes getBandejaAsociada() {
        return bandejaAsociada;
    }
}

package Rol;
import BandejaMensajes.BandejaMensajes;
import Egreso.Core.Egreso;
import Rol.Acciones.LeerMensaje;
import Rol.Acciones.RevisarBandeja;

import java.util.ArrayList;

public class RolRevisorCompra extends Rol{

    private Egreso operacionARevisar;

    public RolRevisorCompra(Egreso operacion) {
        this.operacionARevisar=operacion;


        this.acciones = new ArrayList() {{
            add(new RevisarBandeja(new BandejaMensajes()));
        }};
    }

    public Egreso getOperacionARevisar() {
        return operacionARevisar;
    }
}


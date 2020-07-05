package Rol;

import BandejaMensajes.BandejaMensajes;
import Egreso.Core.Egreso;
import Rol.Acciones.Accion;
import Rol.Acciones.RevisarBandeja;
import Rol.Exepciones.NoTengoPermisosException;
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

    @Override
    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        boolean tengoAccion=acciones.contains(unaAccion);
        if(!tengoAccion){
            throw new NoTengoPermisosException();
        }
    }
}


package Dominio.Rol;

import Dominio.BandejaMensajes.BandejaMensajes;
import Dominio.Egreso.Core.Egreso;
import Dominio.Rol.Acciones.Accion;
import Dominio.Rol.Acciones.RevisarBandeja;
import Dominio.Rol.Exepciones.NoTengoPermisosException;
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


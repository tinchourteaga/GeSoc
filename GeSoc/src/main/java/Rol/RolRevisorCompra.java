package Rol;
import Operacion.Core.Operacion;
import Rol.Acciones.Accion;

import java.util.ArrayList;

public class RolRevisorCompra extends Rol{

    private Operacion operacionARevisar;

    public RolRevisorCompra(Operacion operacion) {
        this.operacionARevisar=operacion;

        this.acciones = new ArrayList() {{
            //add(new DarDeAltaRevisor(); aca metemos las acciones de esta forma
        }};
    }
}


package Rol;
import Egreso.Core.Egreso;

import java.util.ArrayList;

public class RolRevisorCompra extends Rol{

    private Egreso operacionARevisar;

    public RolRevisorCompra(Egreso operacion) {
        this.operacionARevisar=operacion;

        this.acciones = new ArrayList() {{
            //add(new DarDeAltaRevisor(); aca metemos las acciones de esta forma
        }};
    }
}


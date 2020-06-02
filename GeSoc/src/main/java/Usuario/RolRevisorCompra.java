package Usuario;
import Operacion.Core.Operacion;

import java.util.ArrayList;

public class RolRevisorCompra extends Rol{

    private Operacion operacionARevisar;

    public RolRevisorCompra(Operacion operacion) {
        this.operacionARevisar=operacion;

        this.Acciones = new ArrayList() {{
            add(Accion.CREAR_USUARIO);
            add(Accion.REALIZAR_COMPRA);
            add(Accion.REVISAR_COMPRA);
        }};
    }
}


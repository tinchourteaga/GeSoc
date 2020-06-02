package Usuario;
import java.util.ArrayList;
import java.util.List;

public class RolEstandar extends Rol {

    public RolEstandar() {

        this.Acciones = new ArrayList() {{
            add(Accion.CREAR_USUARIO);
        }};
    }
}


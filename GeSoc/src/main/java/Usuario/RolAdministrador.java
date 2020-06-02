package Usuario;
import java.util.ArrayList;
import java.util.List;

public class RolAdministrador extends Rol {
    public RolAdministrador() {

        this.Acciones = new ArrayList() {{
            add(Accion.CREAR_USUARIO);
            add(Accion.REALIZAR_COMPRA);
            add(Accion.REVISAR_COMPRA);
            add(Accion.ASIGNAR_ROL);
        }};
    }
}
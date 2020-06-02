package Usuario;

import java.util.ArrayList;
import java.util.List;

public abstract class Rol {


    protected List<Accion> Acciones= new ArrayList();

    public boolean tengoPermisosPara(final Accion unaAccion){
        return Acciones.stream().anyMatch(accion-> accion.equals(unaAccion));
    }
}

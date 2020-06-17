package Rol;

import Rol.Acciones.Accion;

import java.util.ArrayList;
import java.util.List;

public abstract class Rol {


    protected List<Accion> acciones = new ArrayList();

    public boolean tengoPermisosPara(final Accion unaAccion){
        return acciones.stream().anyMatch(accion-> accion.equals(unaAccion));
    }
}

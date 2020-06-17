package Rol;

import Rol.Acciones.Accion;
import Rol.Exepciones.NoTengoPermisosException;

import java.util.ArrayList;
import java.util.List;

public abstract class Rol {


    public List<Accion> acciones = new ArrayList();

    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        if(!acciones.stream().anyMatch(accion-> accion.equals(unaAccion))){
            throw new NoTengoPermisosException();
        }
    }
}

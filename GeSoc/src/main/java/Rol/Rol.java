package Rol;

import Rol.Acciones.Accion;
import Rol.Exepciones.NoTengoPermisosException;
import Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public abstract class Rol {


    public List<Accion> acciones = new ArrayList();

    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        if(!acciones.stream().anyMatch(accion-> accion.equals(unaAccion))){
            throw new NoTengoPermisosException();
        }
    }
    /* No se si este metodo tiene sentido ponerlo aca o en el usuario lo dejo comentado por las dudas
    public void realizarAccion(Accion unaAccion, Usuario unUsuario) throws NoTengoPermisosException {
        tengoPermisosPara(unaAccion);
        unaAccion.realizar(unUsuario);
    }*/
}

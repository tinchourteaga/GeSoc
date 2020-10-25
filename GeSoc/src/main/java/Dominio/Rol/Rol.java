package Dominio.Rol;

import Dominio.Rol.Acciones.Accion;
import Dominio.Rol.Exepciones.NoTengoPermisosException;

import javax.persistence.*;
import java.util.List;

public abstract class Rol {
    @Transient
    protected List<Accion> acciones;

    @Transient
    protected String nombre;

    public abstract void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException ;
    //se si este metodo tiene sentido ponerlo aca o en el usuario lo dejo comentado por las dudas
    public void realizarAccion(Accion unaAccion) throws NoTengoPermisosException{
        tengoPermisosPara(unaAccion);
        unaAccion.realizar();
    }

    public List<Accion> getAcciones() {
        return acciones;
    }
}

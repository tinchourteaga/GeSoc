package Rol;

import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Rol.Acciones.Accion;
import Rol.Exepciones.NoTengoPermisosException;
import Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public abstract class Rol {


    protected List<Accion> acciones;

    public abstract void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException ;
    //se si este metodo tiene sentido ponerlo aca o en el usuario lo dejo comentado por las dudas
    public void realizarAccion(Accion unaAccion) throws NoTengoPermisosException, NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {
        tengoPermisosPara(unaAccion);
        unaAccion.realizar();
    }

    public List<Accion> getAcciones() {
        return acciones;
    }
}

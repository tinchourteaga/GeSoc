package Dominio.Rol;

import Dominio.Rol.Acciones.Accion;
import Dominio.Rol.Exepciones.NoTengoPermisosException;
import java.util.ArrayList;

public class RolEstandar extends Rol {

    public RolEstandar() {
        this.acciones = new ArrayList<>();
    }


    @Override
    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        throw new NoTengoPermisosException();//el estandar no puede hacer nada
        //solo puede ver la info que provee mi sistema
    }
}


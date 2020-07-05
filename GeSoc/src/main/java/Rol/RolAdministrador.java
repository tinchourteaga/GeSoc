package Rol;

import Rol.Acciones.*;
import Rol.Exepciones.NoTengoPermisosException;
import java.util.ArrayList;

public class RolAdministrador extends Rol {

    public RolAdministrador() {
        this.acciones = new ArrayList<>();//hay que agregar lo que son acciones privilegiadas(?
    }

    @Override
    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        //no hago nada porque como admin siempre tengo permisos
    }
}
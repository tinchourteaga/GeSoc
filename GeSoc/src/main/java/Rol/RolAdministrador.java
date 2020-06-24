package Rol;

import DAO.RepositorioUsuario;
import Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Egreso.Core.CriteriosDeCategorizacion.Jerarquia;
import Egreso.Core.Egreso;
import Rol.Acciones.*;
import Rol.Exepciones.NoTengoPermisosException;
import Usuario.Usuario;

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
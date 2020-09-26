package Dominio.Rol;

import Dominio.Rol.Acciones.*;
import Dominio.Rol.Exepciones.NoTengoPermisosException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("Administrador")
public class RolAdministrador extends Rol {

    public RolAdministrador() {
        this.acciones = new ArrayList<>();//hay que agregar lo que son acciones privilegiadas(?
    }

    @Override
    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        //no hago nada porque como admin siempre tengo permisos
    }
}
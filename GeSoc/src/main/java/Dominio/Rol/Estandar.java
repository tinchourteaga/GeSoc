package Dominio.Rol;

import Dominio.Rol.Acciones.Accion;
import Dominio.Rol.Exepciones.NoTengoPermisosException;

import javax.persistence.Embeddable;
import java.util.ArrayList;

@Embeddable
public class Estandar extends Rol {

    public Estandar() {
        this.acciones = new ArrayList<>();
    }


    @Override
    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        throw new NoTengoPermisosException();//el estandar no puede hacer nada
        //solo puede ver la info que provee mi sistema
    }
}


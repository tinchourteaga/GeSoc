package Dominio.Rol;

import Dominio.Rol.Acciones.Accion;
import Dominio.Rol.Exepciones.NoTengoPermisosException;

import javax.persistence.Embeddable;
import java.util.ArrayList;

@Embeddable
public class Administrador extends Rol {
    public Administrador() {
        this.acciones = new ArrayList<>();//hay que agregar lo que son acciones privilegiadas(?
    }

    @Override
    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        //no hago nada porque como admin siempre tengo permisos
    }

    public void setNombre(String nombre) { this.nombre = nombre; }
}
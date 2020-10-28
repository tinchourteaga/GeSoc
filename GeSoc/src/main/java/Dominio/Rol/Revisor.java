package Dominio.Rol;

import Dominio.Egreso.Core.Egreso;
import Dominio.Rol.Acciones.Accion;
import Dominio.Rol.Exepciones.NoTengoPermisosException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Revisor extends Rol{
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Egreso> egresos;

    public Revisor() {
        this.egresos = new ArrayList<Egreso>();
        this.acciones = new ArrayList();
    }

    public List<Egreso> getOperacionesARevisar() {
        return egresos;
    }

    @Override
    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        boolean tengoAccion=acciones.contains(unaAccion);
        if(!tengoAccion){
            throw new NoTengoPermisosException();
        }
    }

    public void agregarEgreso(Egreso egreso){
        egresos.add(egreso);
    }
}


package Dominio.Rol;

import Dominio.Egreso.Core.Egreso;
import Dominio.Rol.Acciones.Accion;
import Dominio.Rol.Exepciones.NoTengoPermisosException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
@Embeddable
public class RolRevisorCompra extends Rol{
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Egreso> operacionesARevisar;

    public RolRevisorCompra() {
        this.operacionesARevisar = new ArrayList<Egreso>();
        this.acciones = new ArrayList();
    }

    public List<Egreso> getOperacionesARevisar() {
        return operacionesARevisar;
    }

    @Override
    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        boolean tengoAccion=acciones.contains(unaAccion);
        if(!tengoAccion){
            throw new NoTengoPermisosException();
        }
    }

    public void agregarEgreso(Egreso egreso){
        operacionesARevisar.add(egreso);
    }
}


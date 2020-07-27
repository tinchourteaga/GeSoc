package Dominio.Rol;

import Dominio.BandejaMensajes.BandejaMensajes;
import Dominio.Egreso.Core.Egreso;
import Dominio.Rol.Acciones.Accion;
import Dominio.Rol.Acciones.RevisarBandeja;
import Dominio.Rol.Exepciones.NoTengoPermisosException;
import java.util.ArrayList;
import java.util.List;

public class RolRevisorCompra extends Rol{

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


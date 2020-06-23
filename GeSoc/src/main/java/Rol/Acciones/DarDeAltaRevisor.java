package Rol.Acciones;

import Egreso.Core.Egreso;
import Rol.Mensajero;
import Rol.RolRevisorCompra;
import Usuario.Usuario;

import java.util.Date;
import java.util.List;

public class DarDeAltaRevisor implements Accion {
    Usuario revisor;
    Egreso egreso;

    public DarDeAltaRevisor(Usuario revisor) {
        this.revisor = revisor;
    }

    @Override
    public void realizar() {

        //roles compartidos
        //List<RolRevisorCompra> revisores=Mensajero.obtenerRevisoresDe(egreso);
        //RolRevisorCompra rol=revisores.get(0);
        //rol.fechaDeRegistro.put(revisor,new Date());
        //roles excluyentes
        RolRevisorCompra rolRevisor= new RolRevisorCompra(egreso);
        revisor.getRoles().add(rolRevisor);

    }
}

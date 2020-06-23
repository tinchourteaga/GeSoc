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
        //RepositorioUsuario.getTodosLosUsuario().forEach(usuario->crearAccionesRevisores(usuario));

       //  RepositorioEgresos.getTodosLosEgresos().forEach(egreso->RepositorioJerarquia.getTodosLasJerarquias().forEach(jerarquia->crearAccionesJerarquiaAplicar(jerarquia,egreso)));
       // RepositorioCriterios.getTodosLosCriterios().forEach(criterio->RepositorioJerarquia.getTodosLasJerarquias().forEach(jerarquia->crearAccionesJerarquiaAplicar(criterio,jerarquia)));

    }

    //puedo hacer esto
    private void crearAccionesRevisores(Usuario usuario) {
        acciones.add(new DarDeAltaRevisor(usuario));
    }
    private void crearAccionesJerarquiaAplicar(Jerarquia jerarquia, Egreso egreso) {
        acciones.add(new AplicarJerarquia(jerarquia,egreso));
    }
    private void crearAccionesJerarquiaAgregar(Criterio criterio, Jerarquia jerarquia) {
        acciones.add(new AgregarJerarquia(criterio,jerarquia));
    }

    //o esto
    @Override
    public void tengoPermisosPara(Accion unaAccion) throws NoTengoPermisosException {
        //no hago nada porque como admin siempre tengo permisos
    }
}
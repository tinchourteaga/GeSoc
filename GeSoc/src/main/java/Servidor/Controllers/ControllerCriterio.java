package Servidor.Controllers;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Entidad.Entidad;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerCriterio {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        Usuario miUsuario=ControllerSesion.obtenerUsuariodeSesion(request);

        List<Entidad>entidades=miUsuario.getEgresosAREvisar().stream().map(e->e.getEntidad()).collect(Collectors.toList());
        Set<Entidad>entidadesSet=new HashSet<>();
        entidadesSet.addAll(entidades);
        entidades.clear();
        entidades.addAll(entidadesSet);
        datos.put("entidades",entidades);

        ModelAndView vista = new ModelAndView(datos, "crear_criterio.html");

        return vista;
    }

    public static Object crearCriterio(Request request, Response response) {

        String nombreCriterio = request.queryParams("nombreCriterio");
        String descripcion = request.queryParams("descripcion");

        List categoriasAsociadas = new ArrayList();

        Criterio criterio = new Criterio(categoriasAsociadas, nombreCriterio, descripcion);

        persistirCriterio(criterio);

        response.redirect("crear_criterio");

        return null;
    }

    public static void persistirCriterio(Criterio criterio){

        DAO DAOCriterio = new DAOBBDD<Criterio>(Criterio.class); //dao generico de BBDD
        Repositorio repoCriterio = new Repositorio<Criterio>(DAOCriterio); //repositorio que tambien usa generics

        if(!repoCriterio.existe(criterio))
            repoCriterio.agregar(criterio);
    }
}

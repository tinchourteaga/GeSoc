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

        DAO DAOCriterios = new DAOBBDD<Criterio>(Criterio.class); //dao generico de BBDD
        Repositorio repoCriterios = new Repositorio<Criterio>(DAOCriterios); //repositorio que tambien usa generics
        List<Criterio> criterios = repoCriterios.getTodosLosElementos();

        List<Entidad>entidades=miUsuario.getEgresosAREvisar().stream().map(e->e.getEntidad()).collect(Collectors.toList());
        Set<Entidad>entidadesSet=new HashSet<>();
        entidadesSet.addAll(entidades);
        entidades.clear();
        entidades.addAll(entidadesSet);
        datos.put("entidades",entidades);
        datos.put("criterios", criterios);

        ModelAndView vista = new ModelAndView(datos, "crear_criterio.html");

        return vista;
    }

    public static Object crearCriterio(Request request, Response response) {

        String nombreCriterio = request.queryParams("nombreCriterio");
        String descripcion = request.queryParams("descripcion");
        String entidad = request.queryParams("entidad");

        List categoriasAsociadas = new ArrayList();

        Criterio criterio = new Criterio(categoriasAsociadas, nombreCriterio, descripcion);

        Repositorio repoEntidades= new Repositorio(new DAOBBDD<Entidad>(Entidad.class));
        List<Entidad> entidades = repoEntidades.getTodosLosElementos();

        if (!entidades.isEmpty()) {
            Entidad entidadAgregar = entidades.get(0);
            criterio.setEntidad(entidadAgregar);
        }

        Repositorio repoCriterios= new Repositorio(new DAOBBDD<Criterio>(Criterio.class));
        List<Criterio> criteriosHijos = repoCriterios.getTodosLosElementos();

        if (!criteriosHijos.isEmpty()) {
            Criterio criterioHijo = criteriosHijos.get(0);
            criterio.agregarHijos(criterioHijo);
        }

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

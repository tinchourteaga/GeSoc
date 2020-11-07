package Servidor.Controllers;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerCriterio {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
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

        DAO DAOCriterio = new DAOBBDD<Criterio>(); //dao generico de BBDD
        Repositorio repoCriterio = new Repositorio<Criterio>(DAOCriterio); //repositorio que tambien usa generics

        if(!repoCriterio.existe(criterio))
            repoCriterio.agregar(criterio);
    }
}

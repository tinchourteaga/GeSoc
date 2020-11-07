package Servidor.Controllers;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerCategoria {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "crear_categoria.html");

        return vista;
    }

    public static Object crearCategoria(Request request, Response response) {

        String nombreCategoria = request.queryParams("nombreCategoria");
        String descripcion = request.queryParams("descripcion");
        String criterioAsociado = request.queryParams("criterioAsociado");

        CategoriaCriterio categoriaCriterio = new CategoriaCriterio(descripcion,nombreCategoria);

        persistirCategoria(categoriaCriterio, criterioAsociado);

        response.redirect("crear_categoria");

        return null;
    }

    public static void persistirCategoria(CategoriaCriterio categoriaCriterio, String criterioAsociado){

        DAO DAOCriterio = new DAOBBDD<Criterio>(); //dao generico de BBDD
        Repositorio repoCriterio = new Repositorio<Criterio>(DAOCriterio); //repositorio que tambien usa generics

        Criterio criterio = (Criterio)repoCriterio.buscarPorNombre(criterioAsociado);
        Criterio criterioModificado = (Criterio)repoCriterio.buscarPorNombre(criterioAsociado);

        criterioModificado.agregarCategoria(categoriaCriterio);

        //Verificar que esto este bien
        repoCriterio.modificar(criterio, criterioModificado);
    }
}

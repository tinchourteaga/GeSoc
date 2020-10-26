package Servidor.Controllers;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
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

        //persistirCategoria(nombreCategoria, descripcion, criterioAsociado);

        response.redirect("crear_categoria");

        return null;
    }

    public static void persistirCategoria(String nombre, String descripcion, String criterioAsociado){

        CategoriaCriterio categoria = new CategoriaCriterio(descripcion, nombre);

        //PERSISTIRLO
        //Tengo que buscar el criterioAsociado y meterle esta nueva categoria
    }
}

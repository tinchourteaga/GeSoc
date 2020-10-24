package Servidor.Controllers;

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

        System.out.println(request.queryParams("nombreCategoria"));
        System.out.println(request.queryParams("descripcion"));
        System.out.println(request.queryParams("criterioAsociado"));

        String nombreCriterio = request.queryParams("nombreCategoria");
        String descripcion = request.queryParams("descripcion");
        String criterioAsociado = request.queryParams("criterioAsociado");

        response.redirect("crear_categoria");


        return null;
    }
}

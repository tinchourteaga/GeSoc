package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerCriterio {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "crear_criterio.html");

        return vista;
    }

    public static Object crearCriterio(Request request, Response response) {

        System.out.println(request.queryParams("nombreCriterio"));
        System.out.println(request.queryParams("descripcion"));

        String nombreCriterio = request.queryParams("nombreCriterio");
        String descripcion = request.queryParams("descripcion");

        response.redirect("crear_criterio");


        return null;
    }
}

package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerEgresos {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "cargar_egreso.html");

        return vista;
    }

    public static Object cargarEgreso(Request request, Response response) {



        response.redirect("cargar_entidad");


        return null;
    }
}
package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerIngresos {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "cargar_ingreso.html");

        return vista;
    }

    public static Object cargarIngreso(Request request, Response response) {

        String entidad = request.queryParams("entidad");
        String fecha = request.queryParams("fecha");
        String moneda = request.queryParams("moneda");
        String importe = request.queryParams("importe");
        String descripcion = request.queryParams("descripcion");

        response.redirect("cargar_ingreso");

        return null;
    }
}

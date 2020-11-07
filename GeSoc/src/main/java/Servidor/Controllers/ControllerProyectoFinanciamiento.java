package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerProyectoFinanciamiento {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "crear_proyecto_de_financiamiento.html");

        return vista;
    }

    public static Object crearProyectoFinanciamiento(Request request, Response response){

        String entidad = request.queryParams("entidad");
        String fecha = request.queryParams("fecha");
        String moneda = request.queryParams("moneda");
        String total = request.queryParams("total");
        String directorResponsable = request.queryParams("directorResponsable");
        String ingreso = request.queryParams("ingreso"); //necesito tener una lista de ingresos

        

        response.redirect("crear_proyecto_de_financiamiento");

        return null;
    }

    public static void persistirProyectoFinanciamiento(){
        //WTF NO TENEMOS ESTO EN NUESTRO SISTEMA
    }


}

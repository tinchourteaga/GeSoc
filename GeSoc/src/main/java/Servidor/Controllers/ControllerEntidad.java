package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerEntidad {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "cargar_entidad.html");

        return vista;
    }

    public static Object cargarEntidad(Request request, Response response) {

        //Datos principales
        String tipoEntidad = request.queryParams("tipoEntidad");
        String razonSocial = request.queryParams("razonSocial");
        String nombreFicticio = request.queryParams("nombreFicticio");
        String cuilOCuit = request.queryParams("cuilOCuit");
        String codInscripcion = request.queryParams("codInscripcion");

        //Domicilio
        String calle = request.queryParams("calle");
        String numeroCalle = request.queryParams("numeroCalle");
        String piso = request.queryParams("piso");
        String dpto = request.queryParams("dpto");
        String pais = request.queryParams("pais");
        String provincia = request.queryParams("provincia");
        String ciudad = request.queryParams("ciudad");


        response.redirect("cargar_entidad");


        return null;
    }
}

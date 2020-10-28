package Servidor.Controllers;

import Dominio.Ingreso.Ingreso;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        //persistirIngreso(entidad, fecha, moneda, importe, descripcion);

        response.redirect("cargar_ingreso");

        return null;
    }

    public static void persistirIngreso(String entidad, String fecha, String moneda, String importe, String descripcion){
        List egresosAsociados = new ArrayList();

        Ingreso ingreso = new Ingreso(moneda, Double.parseDouble(importe), LocalDate.parse(fecha), descripcion, egresosAsociados);

        //PERSISTIRLO
    }
}

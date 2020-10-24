package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerAsociacion {

    public static ModelAndView visualizarPantallaIngresosYEgresos(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "asociar_ingresos_y_egresos.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaEgresosYPresupuestos(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "asociar_egresos_y_presupuestos.html");

        return vista;
    }

    public static Object asociarEgresosYPresupuestos(Request request, Response response) {

        String entidad = request.queryParams("entidad");
        String fecha = request.queryParams("fecha");
        String metodoPago = request.queryParams("metodoPago");
        String egreso = request.queryParams("egreso");
        String presupuesto = request.queryParams("presupuesto");

        response.redirect("asociar_egresos_y_presupuestos");

        return null;
    }

    public static Object asociarIngresosYEgresos(Request request, Response response) {

        String entidad = request.queryParams("entidad");
        String fecha = request.queryParams("fecha");
        String metodoPago = request.queryParams("metodoPago");
        String egreso = request.queryParams("egreso");
        String ingreso = request.queryParams("ingreso");

        response.redirect("asociar_ingresos_y_egresos");

        return null;
    }
}

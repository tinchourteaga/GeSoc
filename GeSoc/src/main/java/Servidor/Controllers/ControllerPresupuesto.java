package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerPresupuesto {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "cargar_presupuesto.html");

        return vista;
    }

    public static Object cargarPresupuesto(Request request, Response response) {

        String entidad = request.queryParams("entidad");
        String fecha = request.queryParams("fecha");
        String moneda = request.queryParams("moneda");
        String item = request.queryParams("item");
        String valorItem = request.queryParams("valor");
        String proveedor = request.queryParams("proveedor");
        String documentoComercial = request.queryParams("documentoComercial");
        String linkComprobante = request.queryParams("linkComprobante");

        System.out.println(entidad);
        System.out.println(fecha);
        System.out.println(moneda);
        System.out.println(item);
        System.out.println(valorItem);
        System.out.println(proveedor);
        System.out.println(documentoComercial);
        System.out.println(linkComprobante);

        response.redirect("pantalla_principal_usuario");


        return null;
    }
}

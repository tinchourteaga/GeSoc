package Servidor.Controllers;

import Dominio.Egreso.Core.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerEgresos {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "cargar_egreso.html");

        return vista;
    }

    public static Object cargarEgreso(Request request, Response response) {

        String entidad = request.queryParams("seleccionarEntidad");
        String fecha = request.queryParams("fecha");
        String metodoPago = request.queryParams("seleccionarMetodoPago");
        String item = request.queryParams("item");
        String valorItem = request.queryParams("valor");
        String proveedor = request.queryParams("proveedor");
        String documentoComercial = request.queryParams("documentoComercial");
        String descripcionDocComercial = request.queryParams("descripcionDocComercial");

        Egreso egreso = new Egreso(LocalDate.parse(fecha), "Uruguay", 8888, new ArrayList<>(), new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO, "TD"), new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.REMITO, descripcionDocComercial), null);

        if(request.queryParams("esRevisor")!=null){
            //Es revisor
        }

        response.redirect("cargar_egreso");

        return null;
    }
}

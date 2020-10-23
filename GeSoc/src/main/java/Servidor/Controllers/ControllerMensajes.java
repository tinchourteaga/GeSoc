package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerMensajes {

    public static ModelAndView visualizarPantallaMensajesNoRevisor(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "mensajes_no_revisor.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaMensajesRevisor(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "mensajes_revisor.html");

        return vista;
    }
}

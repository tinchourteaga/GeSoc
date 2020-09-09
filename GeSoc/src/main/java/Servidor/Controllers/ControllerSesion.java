package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerSesion{

    public static ModelAndView mostrarLogin(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "index.html");

        return vista;
    }

    public static ModelAndView cerrarSesion(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "logoutHTML");

        return vista;
    }

    public static Object validarLogin(Request request, Response response){

        response.redirect("/inicio");
        //Hacer todo lo de la sesion
        return null;
    }
}
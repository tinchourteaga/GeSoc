package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerUsuario {

    public static ModelAndView visualizarPantallaUsuario(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "usuario.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaAdministrarUsuario(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "administrar_usuarios.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaDatosUsuario(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "datos_usuario.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaConfiguracionGeneral(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "configuracion_general.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaPrincipalUsuario(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "pantalla_principal_usuario.html");

        return vista;
    }
}

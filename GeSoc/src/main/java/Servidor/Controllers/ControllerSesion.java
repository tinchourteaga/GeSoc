package Servidor.Controllers;

import Servidor.Controllers.Hash.FuncionHash;
import Servidor.Controllers.Hash.Hash;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerSesion{

    static Hash encriptador=new FuncionHash();
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
        //System.out.println(request.queryParams("password"));
        String nombreUsuario = request.queryParams("nombreUsuario");
        String contraseniaUsuario = request.queryParams("contraseniaUsuario");




        Boolean usuarioVerificado =verificarDatos(nombreUsuario, contraseniaUsuario);


        if(usuarioVerificado){
            response.redirect("pantalla_principal_usuario");
        }else{
            response.redirect("/autenticacion_usuario");
        }
        return null;
    }

    private static Boolean verificarDatos(String nombreUsuario, String contraseniaUsuario) {

        String contraHasheada= encriptador.funcionHash(contraseniaUsuario);
        //consultas al dao y devolves si existe un usuario con dicha contraseña
        //buscas en la base de datos si existe esa contraseña hasheada con el nombre de usuario TODO
        return true;
    }
}
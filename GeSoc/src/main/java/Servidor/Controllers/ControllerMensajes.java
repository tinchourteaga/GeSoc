package Servidor.Controllers;

import Dominio.Rol.Estandar;
import Dominio.Usuario.Usuario;
import Persistencia.Repos.RepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerMensajes {


    public static ModelAndView visualizarPantallaMensajes(Request request, Response response){

        String nombreUsuario=ControllerSesion.sesiones.keySet().stream().filter(nombre->ControllerSesion.sesiones.get(nombre).equals(request.session())).collect(Collectors.toList()).get(0);
        Usuario usuario= RepositorioUsuario.getInstance().buscarPorUsuario(nombreUsuario);//lo obtenes con el nombre de arriba
        if (Estandar.class.equals(usuario.getRol().getClass())) {
            return visualizarPantallaMensajesNoRevisor(request,response);
        }else{
            return visualizarPantallaMensajesRevisor(request,response,usuario);
        }

    }

    public static ModelAndView visualizarPantallaMensajesNoRevisor(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "mensajes_no_revisor.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaMensajesRevisor(Request request, Response response,Usuario usuario){

        //con el usuario vas sacando datos
        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "mensajes_revisor.html");

        return vista;
    }
}

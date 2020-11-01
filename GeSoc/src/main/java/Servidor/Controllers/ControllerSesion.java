package Servidor.Controllers;

import Dominio.Usuario.Usuario;
import Persistencia.Repos.RepositorioUsuario;
import Servidor.Controllers.Hash.FuncionHash;
import Servidor.Controllers.Hash.Hash;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ControllerSesion{

    static Map<String, Session> sesiones= new HashMap<String,Session>();
    static Hash encriptador=new FuncionHash();
    public static ModelAndView mostrarLogin(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "index.html");

        return vista;
    }

    public static Object cerrarSesion(Request request, Response response){

        Session sesionUsuario=sesiones.remove(request.session().attribute("idUsuarioActual"));
        sesionUsuario.removeAttribute("idUsuarioActual");
        request.session().removeAttribute("idUsuarioActual");
        request.session().invalidate();
        response.redirect("/");
        return null;
    }

    public static Object validarLogin(Request request, Response response){
        System.out.println(request.queryParams("contraseniaUsuario"));
        String nombreUsuario = request.queryParams("nombreUsuario");
        String contraseniaUsuario = request.queryParams("contraseniaUsuario");


        Boolean usuarioVerificado =verificarDatos(nombreUsuario, contraseniaUsuario);


        if(usuarioVerificado && sesiones.containsValue(request.session())){
            Session usuario=request.session(true);
            String id=encriptador.funcionHash((new Date()).toInstant().toString());
            usuario.attribute("idUsuarioActual",id);
            usuario.attribute("nombreUsuario",nombreUsuario);
            sesiones.put(id,usuario);



            response.redirect("pantalla_principal_usuario");
        }else{
            System.out.println("la sesion existia:"+sesiones.get(nombreUsuario));

            response.redirect("autenticacion_usuario");
        }
        return null;
    }

    private static Boolean verificarDatos(String nombreUsuario, String contraseniaUsuario) {

        Usuario unUsuario = RepositorioUsuario.getInstance().buscarPorUsuario(nombreUsuario);

        return unUsuario.getContrasenia().equals(contraseniaUsuario);
    }
}
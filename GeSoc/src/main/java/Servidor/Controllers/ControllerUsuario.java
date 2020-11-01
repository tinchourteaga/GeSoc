package Servidor.Controllers;

import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Rol.Administrador;
import Dominio.Rol.Estandar;
import Dominio.Usuario.Usuario;
import Persistencia.Repos.RepositorioUsuario;
import Servidor.Controllers.Hash.FuncionHash;
import Servidor.Controllers.Hash.Hash;
import Servidor.Controllers.MailSender.SendEmail;
import Servidor.Controllers.PasswordGenerator.PasswordGenerator;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerUsuario {

    static Hash fxHash = new FuncionHash();

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

        String nombre=obtenerNombreUsuario(request);
        datos.put("nombreUsuario",nombre);
        ModelAndView vista = new ModelAndView(datos, "pantalla_principal_usuario.html");

        return vista;
    }

    private static String obtenerNombreUsuario(Request request) {
        return request.queryParams("usuario");
    }

    public static Object cambiarContrasenia(Request request, Response response){
        String contraActual = request.queryParams("contraActual");
        String contraNueva = request.queryParams("contraNueva");
        String verifContraNueva = request.queryParams("verifContraNueva");

        /*
        if(contraActual es correcta -after chequear en la bd- && contraActual.equals(verifContraNueva) && contraActual cumple con las restricciones){
            persisti el cambio en la base luego de hashearla
        }
        */

        System.out.println(request.queryParams("contraActual"));
        System.out.println(request.queryParams("contraNueva"));
        System.out.println(request.queryParams("verifContraNueva"));

        response.redirect("usuario");

        return null;
    }

    public static Object administrarUsuarios(Request request, Response response) throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {

        String apellido = request.queryParams("apellido");
        String nombre = request.queryParams("nombre");
        String dni = request.queryParams("dni");
        String email = request.queryParams("email");
        String telefono = request.queryParams("telefono");
        String empresa = request.queryParams("empresa");
        String rolEnEmpresa = request.queryParams("rolEnEmpresa");
        String nombreUsuario = request.queryParams("usuario");

        if(request.queryParams("checkAdmin") != null){
            persistirUsuarioAdmin(nombre, apellido, nombreUsuario, dni, email);
        }else{
            persistirUsuarioEstandar(nombre, apellido, nombreUsuario, dni, email);
        }

        response.redirect("administrar_usuarios");

        return null;
    }

    public static void persistirUsuarioEstandar(String nombre, String apellido, String nombreUsuario, String dni, String email) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        Estandar rol = new Estandar();
        String contrasenia = PasswordGenerator.generateRandomPassword(10);
        Usuario usuario = new Usuario(rol, nombre, apellido, contrasenia, dni, email);
        usuario.setPersona();

        //chequear si ya existe en la bd -> si existe no lo agrego, sino lo meto
        if(RepositorioUsuario.getInstance().buscarPorUsuario(nombreUsuario) == null) {
            RepositorioUsuario.getInstance().agregar(usuario);
        }

        //Enviamos el mail a la persona con su usuario y contrasenia
        SendEmail.main(email, nombreUsuario, contrasenia);
    }

    public static void persistirUsuarioAdmin(String nombre, String apellido, String nombreUsuario, String dni, String email) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        Administrador rol = new Administrador();
        String contrasenia = PasswordGenerator.generateRandomPassword(10);
        Usuario usuario = new Usuario(rol, nombre, apellido, contrasenia, dni, email);
        usuario.setPersona();

        //chequear si ya existe en la bd -> si existe no lo agrego, sino lo meto
        if(RepositorioUsuario.getInstance().buscarPorUsuario(nombreUsuario) == null) {
            RepositorioUsuario.getInstance().agregar(usuario);
        }

        //Enviamos el mail a la persona con su usuario y contrasenia
        SendEmail.main(email, nombreUsuario, contrasenia);
    }
}

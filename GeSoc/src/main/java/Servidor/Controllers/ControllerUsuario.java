package Servidor.Controllers;

import Dominio.Contrasenia.Core.ValidadorDeContrasenia;
import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Entidad.Entidad;
import Dominio.Usuario.Rol;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import Servidor.Controllers.Hash.FuncionHash;
import Servidor.Controllers.Hash.Hash;
import Servidor.Controllers.MailSender.SendEmail;
import Servidor.Controllers.PasswordGenerator.PasswordGenerator;
import org.eclipse.jetty.http.HttpStatus;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static spark.Spark.halt;

public class ControllerUsuario {

    static Hash fxHash = new FuncionHash();

    public static ModelAndView visualizarPantallaUsuario(Request request, Response response){

        Usuario miUsuario=ControllerSesion.obtenerUsuariodeSesion(request);
        Map<String,Object> datos = new HashMap<>();
        datos.put("nombre",miUsuario.getNombre());
        datos.put("apellido",miUsuario.getApellido());
        datos.put("email",miUsuario.getMail());
        datos.put("rol",miUsuario.getRol());
        datos.put("nickname",miUsuario.getNickName());
        ModelAndView vista = new ModelAndView(datos, "usuario.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaAdministrarUsuario(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "administrar_usuarios.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaDatosUsuario(Request request, Response response) {


        String idUsuario = request.queryParams("us");
        if (!idUsuario.equals(request.session().attribute("idUsuarioActual"))) {
            halt(HttpStatus.NOT_FOUND_404);//estas accediendo con un id que no te corresponde
            return null;
        }
        Usuario miUsuario = ControllerSesion.obtenerUsuariodeSesion(request);

        String idUsuarioAModificar = request.queryParams("usm");

        List<Entidad> entidadesALasQuePertenezco = miUsuario.getEgresosAREvisar().stream().map(e -> e.getEntidad()).collect(Collectors.toList());
        Set<Entidad> setEntidades = new HashSet<Entidad>();
        setEntidades.addAll(entidadesALasQuePertenezco);
        entidadesALasQuePertenezco.clear();
        entidadesALasQuePertenezco.addAll(setEntidades);

        Map<String,Object> datos = new HashMap<>();
        Repositorio repoUsuario = new Repositorio(new DAOBBDD<Usuario>(Usuario.class));
        List<Usuario> todosLosUsuarios = repoUsuario.getTodosLosElementos();
        List<Usuario> usuariosQuePuedoModificar = todosLosUsuarios.stream().filter(usuario -> usuario.getEgresosAREvisar().stream().map(egreso -> egreso.getEntidad()).collect(Collectors.toList()).stream().anyMatch(ent -> entidadesALasQuePertenezco.contains(ent))).collect(Collectors.toList());
        List<Usuario> usuarioAModificarONull = usuariosQuePuedoModificar.stream().filter(usuarioPosible -> usuarioPosible.getUsuario() == Integer.valueOf(idUsuarioAModificar).intValue()).collect(Collectors.toList());
        if (!usuarioAModificarONull.isEmpty()){
            Usuario usuarioPosta = usuarioAModificarONull.get(0);
            datos.put("usuario",usuarioPosta);
    }


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

    public static Object cambiarContrasenia(Request request, Response response) throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {
        String contraActual = request.queryParams("contraActual");
        String contraNueva = request.queryParams("contraNueva");
        String verifContraNueva = request.queryParams("verifContraNueva");

        DAO DAOUsuario = new DAOBBDD<Usuario>(Usuario.class);
        Repositorio repoUsuario = new Repositorio<Usuario>(DAOUsuario);

        Usuario usuario = ControllerSesion.obtenerUsuariodeSesion(request);
        Usuario usuarioModificado = ControllerSesion.obtenerUsuariodeSesion(request);

        if(usuario.getContrasenia().equals(contraActual) && contraActual.equals(verifContraNueva) && ValidadorDeContrasenia.validarContrasenia(contraNueva)){
            //Si pasa todas las validaciones persisto en la db (tengo que hashearla antes?)
            usuarioModificado.setContrasenia(contraNueva);
            repoUsuario.modificar(usuario,usuarioModificado);
        }

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

        String contrasenia = PasswordGenerator.generateRandomPassword(10);
        Usuario usuario = new Usuario(Rol.ESTANDAR, nombre, apellido, contrasenia, dni, email);
        usuario.setPersona();

        DAO DAOUsuario = new DAOBBDD<Usuario>(); //dao generico de BBDD
        Repositorio repoUsuario = new Repositorio<Usuario>(DAOUsuario);//repositorio que tambien usa generics


        //Chequear si ya existe en la bd -> si existe no lo agrego, sino lo meto
        if(!repoUsuario.existe(usuario)){
            repoUsuario.agregar(usuario);
        }

        //Enviamos el mail a la persona con su usuario y contrasenia
        SendEmail.main(email, nombreUsuario, contrasenia);
    }

    public static void persistirUsuarioAdmin(String nombre, String apellido, String nombreUsuario, String dni, String email) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {

        String contrasenia = PasswordGenerator.generateRandomPassword(10);
        Usuario usuario = new Usuario(Rol.ADMINISTRADOR, nombre, apellido, contrasenia, dni, email);
        usuario.setPersona();

        DAO DAOUsuario = new DAOBBDD<Usuario>(); //dao generico de BBDD
        Repositorio repoUsuario = new Repositorio<Usuario>(DAOUsuario);//repositorio que tambien usa generics


        //Chequear si ya existe en la bd -> si existe no lo agrego, sino lo meto
        if(!repoUsuario.existe(usuario)){
            repoUsuario.agregar(usuario);
        }

        //Enviamos el mail a la persona con su usuario y contrasenia
        SendEmail.main(email, nombreUsuario, contrasenia);
    }
}

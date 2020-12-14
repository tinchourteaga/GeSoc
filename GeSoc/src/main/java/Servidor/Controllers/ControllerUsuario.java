package Servidor.Controllers;

import Dominio.Contrasenia.Core.ValidadorDeContrasenia;
import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Egreso.Core.Egreso;
import Dominio.Entidad.Entidad;
import Dominio.Usuario.Rol;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.QueriesUtiles;
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
        String cc= request.queryParamOrDefault("cc","3");
        String error=request.queryParamOrDefault("err","OK");
        String msj="";
        if(cc.equals("0")){
                 switch (error){
                     case "IO":
                         msj="Hubo un error interno y no se pudo cambiar su contraseña. Intente mas tarde.";
                         break;
                     case "NUM":
                         msj="Su contraseña no posee números, intente otra vez.";
                         break;
                     case "LONG":
                         msj="Su contraseña es muy corta, intente otra vez.";
                         break;
                     case "SPC":
                         msj="Su contraseña no posee caracteres especiales, intente otra vez.";
                         break;
                     case "CMN":
                         msj="Su contraseña es muy común, intente otra vez.";
                         break;

                 }
        }else{
            if(cc.equals("1")) {
                msj = "OK";
            }
         }
        datos.put("mensaje",msj);

        ModelAndView vista = new ModelAndView(datos, "usuario.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaAdministrarUsuario(Request request, Response response){

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        Map<String,Object> datos = new HashMap<>();

        if(miUsuario.getRol().equals(Rol.ADMINISTRADOR)) {
            List<Egreso> egreso = QueriesUtiles.obtenerEgresosDe(miUsuario.getNickName());
            List<Entidad> entidades = egreso.stream().map(eg->eg.getEntidad()).collect(Collectors.toList());
            Set<Entidad> entidadesSinRepetidos = new HashSet<>();
            entidadesSinRepetidos.addAll(entidades);
            entidades.clear();
            entidades.addAll(entidadesSinRepetidos);

            Repositorio repoUsuario = new Repositorio(new DAOBBDD<Usuario>(Usuario.class));
            List<Usuario> usuariosPosibles = repoUsuario.getTodosLosElementos();
            List<Usuario> usuarioAVer = usuariosPosibles.stream().filter(us -> us.getEntidades().stream().anyMatch(e -> entidades.contains(e))).collect(Collectors.toList());

            String entidadFiltro = request.queryParams("entidadFiltro");
            String apellidoFiltro = request.queryParamOrDefault("apellido", "Ingrese apellido");
            String nombreFiltro = request.queryParamOrDefault("nombre", "Ingrese nombre");
            String identificacion = request.queryParamOrDefault("identificacion", "Ingrese Nombre de Usuario");
            String admin = request.queryParams("filtroAdministrador");
            String revisor = request.queryParams("filtroRevisor");

            if (admin != null && admin.equals("true")) {
                usuarioAVer = usuarioAVer.stream().filter(us -> us.getRol().equals(Rol.ADMINISTRADOR)).collect(Collectors.toList());
                datos.put("checkbox_admin", admin);
            }
            if (revisor != null && revisor.equals("true")) {
                usuarioAVer = usuarioAVer.stream().filter(us -> !QueriesUtiles.obtenerEgresosDe(us.getNickName()).isEmpty()).collect(Collectors.toList());
                datos.put("checkbox_revisor", revisor);
            }

            // ESTÁ OK
            if (entidadFiltro != null && !entidadFiltro.equals("Seleccione una empresa") && !entidadFiltro.equals("selected")) {
                List<Entidad> posiblesEntidadesAfiltrar = entidades.stream().filter(e -> e.getEntidad() == Integer.valueOf(entidadFiltro).intValue()).collect(Collectors.toList());
                if (!posiblesEntidadesAfiltrar.isEmpty()) {
                    Entidad entidadAfiltrar = posiblesEntidadesAfiltrar.get(0);
                    usuarioAVer = usuarioAVer.stream().filter(unUs -> unUs.getEntidades().stream().anyMatch(e -> entidades.contains(entidadAfiltrar))).collect(Collectors.toList());
                    datos.put("empresaElegida", entidadAfiltrar);
                }
            }

            if (!apellidoFiltro.equals("Ingrese apellido") && !apellidoFiltro.equals("Ingrese")) {
                usuarioAVer = usuarioAVer.stream().filter(us -> us.getApellido().equals(apellidoFiltro)).collect(Collectors.toList());
            }
            datos.put("apellidoDefaultFiltro", apellidoFiltro);

            if (!identificacion.equals("Ingrese Nombre de Usuario") && !identificacion.equals("Ingrese")) {
                usuarioAVer = usuarioAVer.stream().filter(us -> us.getNickName().equals(identificacion)).collect(Collectors.toList());
            }
            datos.put("identificacionDefaultFiltro", identificacion);

            if (!nombreFiltro.equals("Ingrese nombre") && !nombreFiltro.equals("Ingrese")) {

                usuarioAVer = usuarioAVer.stream().filter(us -> us.getNombre().equals(nombreFiltro)).collect(Collectors.toList());
            }
            datos.put("nombreDefaultFiltro", nombreFiltro);

            datos.put("usuarios", usuarioAVer);
            datos.put("entidades", entidades);
        }else{
            response.redirect("/pantalla_principal_usuario");
        }
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

        List<Entidad> entidadesALasQuePertenezco = miUsuario.getEntidades();
        Set<Entidad> setEntidades = new HashSet<Entidad>();
        setEntidades.addAll(entidadesALasQuePertenezco);
        entidadesALasQuePertenezco.clear();
        entidadesALasQuePertenezco.addAll(setEntidades);

        Map<String,Object> datos = new HashMap<>();
        Repositorio repoUsuario = new Repositorio(new DAOBBDD<Usuario>(Usuario.class));
        List<Usuario> todosLosUsuarios = repoUsuario.getTodosLosElementos();
        List<Usuario> usuariosQuePuedoModificar = todosLosUsuarios.stream().filter(usuario -> QueriesUtiles.obtenerEgresosDe(usuario.getNickName()).stream().map(egreso -> egreso.getEntidad()).collect(Collectors.toList()).stream().anyMatch(ent -> entidadesALasQuePertenezco.contains(ent))).collect(Collectors.toList());
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

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        String nombre=miUsuario.getNombre();
        datos.put("nombreUsuario",nombre);
        ModelAndView vista = new ModelAndView(datos, "pantalla_principal_usuario.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaAltaUsuario(Request request, Response response) {
        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        Map<String,Object> datos = new HashMap<>();

        List<Entidad> misEntidades = miUsuario.getEntidades();
        datos.put("entidades", misEntidades);

        ModelAndView vista = new ModelAndView(datos, "alta_usuarios.html");

        return vista;
    }

   /* private static String obtenerNombreUsuario(Request request) {
        return request.queryParams("usuario");
    }*/

    public static Object cambiarContrasenia(Request request, Response response){
        String contraActual = request.queryParams("contraActual");
        String contraNueva = request.queryParams("contraNueva");
        String verifContraNueva = request.queryParams("verifContraNueva");

        DAO DAOUsuario = new DAOBBDD<Usuario>(Usuario.class);
        Repositorio repoUsuario = new Repositorio<Usuario>(DAOUsuario);

        Usuario usuario = ControllerSesion.obtenerUsuariodeSesion(request);
        Usuario usuarioModificado = ControllerSesion.obtenerUsuariodeSesion(request);

        int cc=0;
        try {
            if(usuario.getContrasenia().equals(contraActual) && contraNueva.equals(verifContraNueva) && ValidadorDeContrasenia.validarContrasenia(contraNueva)){
                usuarioModificado.setContrasenia(contraNueva);
                repoUsuario.modificar(usuario,usuarioModificado);
                cc=1;
                response.redirect("usuario?cc="+cc);
            }
        } catch (IOException e) {
            response.redirect("usuario?cc="+cc+"&err=IO");
        } catch (ExcepcionNumero excepcionNumero) {
            response.redirect("usuario?cc="+cc+"&err=NUM");
        } catch (ExcepcionLongitud excepcionLongitud) {
            response.redirect("usuario?cc="+cc+"&err=LONG");
        } catch (ExcepcionCaracterEspecial excepcionCaracterEspecial) {
            response.redirect("usuario?cc="+cc+"&err=SPC");
        } catch (ExcepcionContraseniaComun excepcionContraseniaComun) {
            response.redirect("usuario?cc="+cc+"&err=CMN");
        }
        response.redirect("usuario");
        return null;
    }

    public static Object administrarUsuarios(Request request, Response response) throws ExcepcionNumero, ExcepcionContraseniaComun, ExcepcionLongitud, ExcepcionCaracterEspecial, IOException {

        String apellido = request.queryParams("apellidoAlta");
        String nombre = request.queryParams("nombreAlta");
        String dni = request.queryParams("dniAlta");
        String email = request.queryParams("emailAlta");
        String empresa = request.queryParams("empresaAlta");
        String nombreUsuario = request.queryParams("usuarioAlta"); // BORRAR para que le de uno autogenerado

        if(request.queryParams("checkAdmin") != null){
            persistirUsuarioAdmin(nombre, apellido, nombreUsuario, dni, email, empresa);
        }else{
            persistirUsuarioEstandar(nombre, apellido, nombreUsuario, dni, email, empresa);
        }

        response.redirect("administrar_usuarios");

        return null;
    }

    public static void persistirUsuarioEstandar(String nombre, String apellido, String nombreUsuario, String dni, String email, String empresa) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {

        String contrasenia = PasswordGenerator.generateRandomPassword(10);
        Usuario usuario = new Usuario(Rol.ESTANDAR, nombre, apellido, contrasenia, dni, email);
        usuario.setPersona();

        Repositorio repoEntidades= new Repositorio(new DAOBBDD<Entidad>(Entidad.class));
        List<Entidad> entidades = repoEntidades.getTodosLosElementos();

        if (!entidades.isEmpty()) {
            Entidad entidadAgregar = entidades.get(0);
            usuario.agregarEntidades(entidadAgregar);
        }

        DAO DAOUsuario = new DAOBBDD<Usuario>(Usuario.class);
        Repositorio repoUsuario = new Repositorio<Usuario>(DAOUsuario);

        //Chequear si ya existe en la bd -> si existe no lo agrego, sino lo meto
        if(!repoUsuario.existe(usuario)){
            repoUsuario.agregar(usuario);
        }

        //Enviamos el mail a la persona con su usuario y contrasenia
        SendEmail.main(email, usuario.getNickName(), contrasenia);
    }

    public static void persistirUsuarioAdmin(String nombre, String apellido, String nombreUsuario, String dni, String email, String empresa) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {

        String contrasenia = PasswordGenerator.generateRandomPassword(10);
        Usuario usuario = new Usuario(Rol.ADMINISTRADOR, nombre, apellido, contrasenia, dni, email);
        usuario.setPersona();

        Repositorio repoEntidades= new Repositorio(new DAOBBDD<Entidad>(Entidad.class));
        List<Entidad> entidades = repoEntidades.getTodosLosElementos();

        if (!entidades.isEmpty()) {
            Entidad entidadAgregar = entidades.get(0);
            usuario.agregarEntidades(entidadAgregar);
        }

        DAO DAOUsuario = new DAOBBDD<Usuario>(Usuario.class);
        Repositorio repoUsuario = new Repositorio<Usuario>(DAOUsuario);

        //Chequear si ya existe en la bd -> si existe no lo agrego, sino lo meto
        if(!repoUsuario.existe(usuario)){
            repoUsuario.agregar(usuario);
        }

        //Enviamos el mail a la persona con su usuario y contrasenia
        SendEmail.main(email, usuario.getNickName(), contrasenia);
    }
}

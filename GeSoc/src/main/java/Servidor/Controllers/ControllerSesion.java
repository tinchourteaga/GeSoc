package Servidor.Controllers;

import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import Servidor.Controllers.Hash.FuncionHash;
import Servidor.Controllers.Hash.Hash;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static Persistencia.EntityManagerHelper.getEntityManager;

public class ControllerSesion{

    static Map<String, Session> sesiones= new HashMap<String,Session>();
    static Map<String, Boolean> usuariosActivos= new HashMap<String,Boolean>();

    static Hash encriptador=new FuncionHash();
    public static ModelAndView mostrarLogin(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "index.html");

        return vista;
    }

    public static Object cerrarSesion(Request request, Response response){

        Session sesionUsuario=sesiones.remove(request.session().attribute("idUsuarioActual"));
        sesionUsuario.removeAttribute("idUsuarioActual");
        String nombreUsuario= request.session().attribute("nombreUsuario"); //si no funca usa sesionUsuario en vez de request
        Boolean estaActivo=usuariosActivos.get(nombreUsuario);
        estaActivo=Boolean.FALSE;
        usuariosActivos.put(nombreUsuario,Boolean.FALSE);
        request.session().invalidate();
        response.redirect("/");
        return null;
    }

    public static Object validarLogin(Request request, Response response){
        System.out.println(request.queryParams("contraseniaUsuario"));
        String nombreUsuario = request.queryParams("nombreUsuario");
        String contraseniaUsuario = request.queryParams("contraseniaUsuario");


        boolean usuarioVerificado =verificarDatos(nombreUsuario, contraseniaUsuario);
        boolean estaActivo= usuariosActivos.getOrDefault(nombreUsuario,Boolean.FALSE).equals(Boolean.TRUE);//quizas es medio choto esto y vas a tener que usar un getBooleanValue

        if(usuarioVerificado && !estaActivo){
            Session usuario=request.session(true);
            String id=encriptador.funcionHash((new Date()).toInstant().toString());
            usuariosActivos.put(nombreUsuario,Boolean.TRUE);
            usuario.attribute("idUsuarioActual",id);
            usuario.attribute("nombreUsuario",nombreUsuario);
            sesiones.put(id,usuario);



            response.redirect("pantalla_principal_usuario");
        }else{
            System.out.println("la sesion existia");

            response.redirect("autenticacion_usuario");
        }
        return null;
    }

    private static boolean verificarDatos(String nombreUsuario, String contraseniaUsuario) {


        DAO DAOUsuario = new DAOBBDD<Usuario>(Usuario.class); //dao generico de BBDD
        Repositorio repoUsuario = new Repositorio<Usuario>(DAOUsuario);//repositorio que tambien usa generics
        List<Usuario> todosLosUsuarios = repoUsuario.getTodosLosElementos();
        List<Usuario> usuariosPosibles=todosLosUsuarios.stream().filter(us-> us.getNickName().equals(nombreUsuario)).collect(Collectors.toList());
        if(usuariosPosibles.isEmpty()){return false;}//no puedo usar el otro metodo porque no le autorice la sesion
        Usuario unUsuario= usuariosPosibles.get(0);
        boolean valor=unUsuario.getContrasenia().equals(contraseniaUsuario);
        return valor;
    }

    public static Usuario obtenerUsuariodeSesion(Request request){
        String username = request.session().attribute("nombreUsuario");

        //Hacer un query con el nombre de usuario de sesion para sacar su PK
        String queryString = "SELECT usuario FROM Usuario p WHERE p.persona = :username";

        TypedQuery<Integer> query = getEntityManager().createQuery(queryString, Integer.class);

        query.setParameter("username", username);

        List<Integer> usuariosLista = query.getResultList();

        int usuarioPK = usuariosLista.get(0);

        EntityManager em = getEntityManager();
        Usuario user = em.find(Usuario.class, usuarioPK);

        return user;

    }
}
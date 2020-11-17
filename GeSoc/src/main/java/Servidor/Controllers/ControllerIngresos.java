package Servidor.Controllers;

import Dominio.Entidad.Entidad;
import Dominio.Ingreso.Ingreso;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ControllerIngresos {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);

        List<Entidad> entidades= miUsuario.getEgresosAREvisar().stream().map(e->e.getEntidad()).collect(Collectors.toList());

        Set<Entidad> setEntidades= new HashSet<Entidad>();
        setEntidades.addAll(entidades);
        entidades.clear();
        entidades.addAll(setEntidades);

        datos.put("entidades",entidades);
        datos.put("egreso",miUsuario.getEgresosAREvisar());

        ModelAndView vista = new ModelAndView(datos, "cargar_ingreso.html");

        return vista;
    }

    public static Object cargarIngreso(Request request, Response response) {

        String entidad = request.queryParams("entidad"); //No lo tengo en mi constructor -> es necesario?
        String fecha = request.queryParams("fecha");
        String moneda = request.queryParams("moneda");
        String importe = request.queryParams("importe");
        String descripcion = request.queryParams("descripcion");

        List egresosAsociados = new ArrayList();

        Ingreso ingreso = new Ingreso(moneda, Double.parseDouble(importe), LocalDate.parse(fecha),LocalDate.now(), descripcion, egresosAsociados);

        persistirIngreso(ingreso);

        response.redirect("cargar_ingreso");

        return null;
    }

    public static void persistirIngreso(Ingreso ingreso){

        DAO DAOIngreso = new DAOBBDD<Ingreso>(Ingreso.class); //dao generico de BBDD
        Repositorio repoIngreso = new Repositorio<Ingreso>(DAOIngreso); //repositorio que tambien usa generics

        if(!repoIngreso.existe(ingreso))
            repoIngreso.agregar(ingreso);

    }
}

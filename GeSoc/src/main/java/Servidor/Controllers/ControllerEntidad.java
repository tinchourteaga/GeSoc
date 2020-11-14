package Servidor.Controllers;

import Dominio.Entidad.*;
import Lugares.Pais;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerEntidad {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "cargar_entidad.html");

        List<Pais> paises = new ArrayList<>();

        Pais alemania = new Pais("", "Alemania", "", "");
        Pais argentina = new Pais("", "Argentina", "", "");
        Pais brasil = new Pais("", "Brasil", "", "");

        paises.add(alemania);
        paises.add(argentina);
        paises.add(brasil);

        datos.put("paises",paises);

        return vista;
    }

    public static Object cargarEntidad(Request request, Response response) {

        //Datos principales
        String tipoEntidad = request.queryParams("tipoEntidad");
        String razonSocial = request.queryParams("razonSocial");
        String descripcion = request.queryParams("descripcion");
        String nombreFicticio = request.queryParams("nombreFicticio");
        String cuilOCuit = request.queryParams("cuilOCuit");
        String codInscripcion = request.queryParams("codInscripcion");


        //Domicilio
        String calle = request.queryParams("calle");
        String numeroCalle = request.queryParams("numeroCalle");
        String piso = request.queryParams("piso");
        String dpto = request.queryParams("dpto");
        String pais = request.queryParams("pais");
        String provincia = request.queryParams("provincia");
        String ciudad = request.queryParams("ciudad");

        DireccionPostal nuevaDir=ControllerDirecciones.generarDireccion(calle,numeroCalle,piso,dpto,pais,provincia,ciudad);


        switch(tipoEntidad){
            case "Entidad Base":
                EntidadBase nuevaEntidadBase=new EntidadBase(nombreFicticio,descripcion,null);
                persistirEntidadBase(nuevaEntidadBase);
            break;
            case "Empresa":
                Empresa tipoEmpresa=new Empresa();
                tipoEmpresa.setDireccionPostal(nuevaDir);
                EntidadJuridica nuevaEmpresa = new EntidadJuridica(nombreFicticio, descripcion, tipoEmpresa);
                persistirEntidadJuridica(nuevaEmpresa);
            break;
            case "Organizacion Social":
                OrganizacionSocial nuevaorg=new OrganizacionSocial();
                nuevaorg.setDireccionPostal(nuevaDir);
                EntidadJuridica nuevaOrganizacionSocial = new EntidadJuridica(nombreFicticio, descripcion, nuevaorg);
                persistirEntidadJuridica(nuevaOrganizacionSocial);
            break;
        }

        response.redirect("cargar_entidad");


        return null;
    }

    private static void persistirEntidadJuridica(EntidadJuridica nuevaEntidadJuridica) {

        DAO DAOEntidadJuridica = new DAOBBDD<EntidadJuridica>(); //dao generico de BBDD
        Repositorio repoEntidadJuridica = new Repositorio<EntidadJuridica>(DAOEntidadJuridica); //repositorio que tambien usa generics

        if(!repoEntidadJuridica.existe(nuevaEntidadJuridica))
            repoEntidadJuridica.agregar(nuevaEntidadJuridica);
    }

    private static void persistirEntidadBase(EntidadBase nuevaEntidadBase) {

        DAO DAOEntidadBase = new DAOBBDD<EntidadBase>(); //dao generico de BBDD
        Repositorio repoEntidadBase = new Repositorio<EntidadBase>(DAOEntidadBase); //repositorio que tambien usa generics

        if(!repoEntidadBase.existe(nuevaEntidadBase))
            repoEntidadBase.agregar(nuevaEntidadBase);
    }
}

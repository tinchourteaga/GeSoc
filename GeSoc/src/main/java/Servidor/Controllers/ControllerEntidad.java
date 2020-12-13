package Servidor.Controllers;

import Dominio.Entidad.*;
import Dominio.Usuario.Usuario;
import Lugares.Ciudad;
import Lugares.Pais;
import Lugares.Provincia;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.QueriesUtiles;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ControllerEntidad {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "cargar_entidad.html");

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);

        DAO DAOEntidadBase = new DAOBBDD<EntidadBase>(EntidadBase.class); //dao generico de BBDD
        Repositorio repoEntidadBase = new Repositorio<EntidadBase>(DAOEntidadBase); //repositorio que tambien usa generics
        List<EntidadBase> entidadesBase = repoEntidadBase.getTodosLosElementos();

        entidadesBase= entidadesBase.stream().filter(entidadBase -> QueriesUtiles.obtenerEntidadDeUsuario(miUsuario).contains(entidadBase)).collect(Collectors.toList());

        DAO DAOEntidadJuridica = new DAOBBDD<EntidadJuridica>(EntidadJuridica.class); //dao generico de BBDD
        Repositorio repoEntidadJuridica = new Repositorio<EntidadJuridica>(DAOEntidadJuridica); //repositorio que tambien usa generics
        List<EntidadJuridica> entidadesJuridicas = repoEntidadJuridica.getTodosLosElementos();

        entidadesJuridicas= entidadesJuridicas.stream().filter(entidadJuridica -> QueriesUtiles.obtenerEntidadDeUsuario(miUsuario).contains(entidadJuridica)).collect(Collectors.toList());


        Repositorio repoPaises=new Repositorio(new DAOBBDD<Pais>(Pais.class));
        List<Pais> paises = repoPaises.getTodosLosElementos();
        List<Ciudad> ciudades;

        String paisElegido= request.queryParams("pais");
        Pais paisElegidoObj=null;
       if(paisElegido!=null) {
           paisElegidoObj = paises.stream().filter(p -> p.getPais()==Integer.valueOf(paisElegido).intValue()).collect(Collectors.toList()).get(0);
           datos.put("paisElegido",paisElegidoObj);
           datos.put("provincias",paisElegidoObj.getProvincias());
       }
        String provinciaElegida= request.queryParams("provincia");
        if(provinciaElegida!=null && paisElegidoObj!=null){

            List<Provincia> provinciasPosibles = paisElegidoObj.getProvincias().stream().filter(p->p.getProvincia()==Integer.valueOf(provinciaElegida).intValue()).collect(Collectors.toList());
            if(!provinciasPosibles.isEmpty()){
                Provincia provinciaElegidaobj=provinciasPosibles.get(0);
                datos.put("provinciaElegida",provinciaElegidaobj);
                datos.put("ciudades", provinciaElegidaobj.getCiudades());
        }
        }
       String tipoEntidad= request.queryParams("entidad");

        //saco los campos que pudieron completar antes


        Optional<String> rs=    Optional.ofNullable(request.queryParams("razonSocial"));
        Optional<String> rf=    Optional.ofNullable(request.queryParams("nombreFicticio"));
        Optional<String> cuit=  Optional.ofNullable(request.queryParams("cuilOCuit"));
        Optional<String> codIGJ=Optional.ofNullable(request.queryParams("codInscripcion"));
        Optional<String> calle= Optional.ofNullable(request.queryParams("calle"));
        Optional<String> piso=  Optional.ofNullable(request.queryParams("piso"));
        Optional<String> numero=Optional.ofNullable(request.queryParams("numeroCalle"));
        Optional<String> depto= Optional.ofNullable(request.queryParams("dpto"));

        datos.put("razonSocialDefault",rs.orElse(""));
        datos.put("nombreFicticioDefault",rf.orElse(""));
        datos.put("cuitDefault",cuit.orElse(""));
        datos.put("codigoIGJDefault",codIGJ.orElse(""));
        datos.put("calleDefault",calle.orElse(""));
        datos.put("pisoDefault",piso.orElse(""));
        datos.put("numeroDefault",numero.orElse(""));
        datos.put("deptoDefault",depto.orElse(""));

        datos.put("paises",paises);
        datos.put("tipoEntidad",tipoEntidad);
        datos.put("entidades_base", entidadesBase);
        datos.put("entidades_juridicas", entidadesJuridicas);
        datos.put("entidadesJuridicas",entidadesJuridicas);

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

        DireccionPostal nuevaDir;
        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);

        if(tipoEntidad==null){
            String entidadJuridicaAsociada= request.queryParams("entidadJuridicaAsociada");
            Optional<Entidad> entidadJuridica=miUsuario.getEntidades().stream().filter(entidad->entidad.getEntidad()==Integer.valueOf(entidadJuridicaAsociada).intValue()).findFirst();
            EntidadBase nuevaEntidadBase = new EntidadBase(request.queryParams("nombreFicticio1"), descripcion, (EntidadJuridica)entidadJuridica.get());
            //nuevaEntidadBase.agregarEmpleado(miUsuario);
            miUsuario.agregarEntidades(nuevaEntidadBase);
            persistirEntidadBase(nuevaEntidadBase);

        }else {

            switch (tipoEntidad) {
                case "Empresa":
                    Empresa tipoEmpresa = new Empresa();
                    nuevaDir = ControllerDirecciones.generarDireccion(calle, numeroCalle, piso, dpto, pais, provincia, ciudad);
                    tipoEmpresa.setDireccionPostal(nuevaDir);
                    tipoEmpresa.setCodigoDeInscripcion(codInscripcion);
                    tipoEmpresa.setCuit(cuilOCuit);
                    tipoEmpresa.setRazonSocial(razonSocial);
                    EntidadJuridica nuevaEmpresa = new EntidadJuridica(nombreFicticio, descripcion, tipoEmpresa);
                    //nuevaEmpresa.agregarEmpleado(miUsuario);
                    miUsuario.agregarEntidades(nuevaEmpresa);
                    persistirEntidadJuridica(nuevaEmpresa);
                    break;
                case "OrganizacionSocial":
                    OrganizacionSocial nuevaorg = new OrganizacionSocial();
                    nuevaDir = ControllerDirecciones.generarDireccion(calle, numeroCalle, piso, dpto, pais, provincia, ciudad);
                    nuevaorg.setDireccionPostal(nuevaDir);
                    nuevaorg.setCodigoDeInscripcion(codInscripcion);
                    nuevaorg.setCuit(cuilOCuit);
                    nuevaorg.setRazonSocial(razonSocial);
                    EntidadJuridica nuevaOrganizacionSocial = new EntidadJuridica(nombreFicticio, descripcion, nuevaorg);
                    //nuevaOrganizacionSocial.agregarEmpleado(miUsuario);
                    miUsuario.agregarEntidades(nuevaOrganizacionSocial);
                    persistirEntidadJuridica(nuevaOrganizacionSocial);
                    break;
            }
        }
        response.redirect("cargar_entidad");


        return null;
    }

    private static void persistirEntidadJuridica(EntidadJuridica nuevaEntidadJuridica) {

        DAO DAOEntidadJuridica = new DAOBBDD<EntidadJuridica>(EntidadJuridica.class); //dao generico de BBDD
        Repositorio repoEntidadJuridica = new Repositorio<EntidadJuridica>(DAOEntidadJuridica); //repositorio que tambien usa generics

        if(!repoEntidadJuridica.existe(nuevaEntidadJuridica))
            repoEntidadJuridica.agregar(nuevaEntidadJuridica);
    }

    private static void persistirEntidadBase(EntidadBase nuevaEntidadBase) {

        DAO DAOEntidadBase = new DAOBBDD<EntidadBase>(EntidadBase.class); //dao generico de BBDD
        Repositorio repoEntidadBase = new Repositorio<EntidadBase>(DAOEntidadBase); //repositorio que tambien usa generics

        if(!repoEntidadBase.existe(nuevaEntidadBase))
            repoEntidadBase.agregar(nuevaEntidadBase);
    }
}

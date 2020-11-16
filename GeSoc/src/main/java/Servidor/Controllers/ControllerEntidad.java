package Servidor.Controllers;

import Dominio.Entidad.*;
import Lugares.Ciudad;
import Lugares.Pais;
import Lugares.Provincia;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerEntidad {
    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "cargar_entidad.html");

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


        String rs=request.queryParamOrDefault("razonSocial","Ingrese razon social");
        String rf=request.queryParamOrDefault("nombreFicticio","Ingrese nombre ficticio");
        String cuit=request.queryParamOrDefault("cuilOCuit","Ingrese  CUIL o CUIT");
        String codIGJ=request.queryParamOrDefault("codInscripcion","Ingrese codigo de IGJ");
        String calle=request.queryParamOrDefault("calle","Ingrese calle");
        String piso=request.queryParamOrDefault("piso","piso");
        String numero=request.queryParamOrDefault("numeroCalle","nro");
        String depto=request.queryParamOrDefault("dpto","depto");


        datos.put("paises",paises);
        datos.put("tipoEntidad",tipoEntidad);
        datos.put("razonSocialDefault",rs);
        datos.put("nombreFicticioDefault",rf);
        datos.put("cuitDefault",cuit);
        datos.put("codigoIGJDefault",codIGJ);
        datos.put("calleDefault",calle);
        datos.put("pisoDefault",piso);
        datos.put("deptoDefault",depto);
        datos.put("numeroDefault",numero);

        return vista;
    }

    public static Object cargarEntidad(Request request, Response response) {

        //Datos principales
        String tipoEntidad = request.queryParams("entidad");
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
                tipoEmpresa.setCodigoDeInscripcion(codInscripcion);
                tipoEmpresa.setCuit(cuilOCuit);
                tipoEmpresa.setRazonSocial(razonSocial);
                EntidadJuridica nuevaEmpresa = new EntidadJuridica(nombreFicticio, descripcion, tipoEmpresa);
                persistirEntidadJuridica(nuevaEmpresa);
            break;
            case "Organizacion Social":
                OrganizacionSocial nuevaorg=new OrganizacionSocial();
                nuevaorg.setDireccionPostal(nuevaDir);
                nuevaorg.setCodigoDeInscripcion(codInscripcion);
                nuevaorg.setCuit(cuilOCuit);
                nuevaorg.setRazonSocial(razonSocial);
                EntidadJuridica nuevaOrganizacionSocial = new EntidadJuridica(nombreFicticio, descripcion, nuevaorg);
                persistirEntidadJuridica(nuevaOrganizacionSocial);
            break;
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

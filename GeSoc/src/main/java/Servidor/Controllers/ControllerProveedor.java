package Servidor.Controllers;

import Dominio.Egreso.Core.Proveedor;
import Dominio.Entidad.DireccionPostal;
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
import java.util.Optional;
import java.util.stream.Collectors;

public class ControllerProveedor {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();

        Repositorio repoPaises= new Repositorio(new DAOBBDD<Pais>(Pais.class));
        List<Pais> paises = repoPaises.getTodosLosElementos();

        //me fijo si cargo cosas


        List<Ciudad> ciudades;
        String paisElegido= request.queryParams("pais");
        Pais paisElegidoObj=null;
        if(paisElegido!=null && !paisElegido.equals("")) {
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
        String tipoEntidad= request.queryParamOrDefault("tipo","");


        String esPersona="";
        String esEmpresa="";

        if(tipoEntidad.equals("Persona")){
            esPersona=tipoEntidad;
        }
        if(tipoEntidad.equals("Empresa")){
            esEmpresa=tipoEntidad;
        }
        //saco los campos que pudieron completar antes


        Optional<String> rs=        Optional.ofNullable(request.queryParams("razonSocial"));
        Optional<String> rf=        Optional.ofNullable(request.queryParams("nombreFicticio"));
        Optional<String> cuit=      Optional.ofNullable(request.queryParams("cuilOCuit"));
        Optional<String> calle=     Optional.ofNullable(request.queryParams("calle"));
        Optional<String> piso=      Optional.ofNullable(request.queryParams("piso"));
        Optional<String> numero=    Optional.ofNullable(request.queryParams("numeroCalle"));
        Optional<String> depto=     Optional.ofNullable(request.queryParams("dpto"));
        Optional<String> apellido=  Optional.ofNullable(request.queryParams("Apellido"));
        Optional<String> nombre=    Optional.ofNullable(request.queryParams("nombreFicticio"));
        Optional<String> dni=       Optional.ofNullable(request.queryParams("DNI"));
        Optional<String> callePers= Optional.ofNullable(request.queryParams("callePers"));
        Optional<String> pisoPers=  Optional.ofNullable(request.queryParams("pisoPers"));
        Optional<String> numeroPers=Optional.ofNullable(request.queryParams("numeroPers"));
        Optional<String> deptoPers= Optional.ofNullable(request.queryParams("deptoPers"));


        datos.put("paises",paises);
        datos.put("esEmpresa",esEmpresa);
        datos.put("razonSocialDefault",rs.orElse(""));
        datos.put("nombreFicticioDefault",rf.orElse(""));
        datos.put("cuitDefault",cuit.orElse(""));
        datos.put("calleDefault",calle.orElse(""));
        datos.put("pisoDefault",piso.orElse(""));
        datos.put("deptoDefault",depto.orElse(""));
        datos.put("numeroDefault",numero.orElse(""));

        datos.put("esPersona",esPersona);
        datos.put("apellido",apellido.orElse(""));
        datos.put("nombre",nombre.orElse(""));
        datos.put("dni",dni.orElse(""));
        datos.put("callePers",callePers.orElse(""));
        datos.put("pisoPers",pisoPers.orElse(""));
        datos.put("deptoPers",deptoPers.orElse(""));
        datos.put("numeroPers",numeroPers.orElse(""));

        datos.put("paises",paises);
        ModelAndView vista = new ModelAndView(datos, "cargar_proveedor.html");

        return vista;
    }

    public static Object cargarProveedor(Request request, Response response) {

        String calle, numero, piso, departamento, pais, provincia, ciudad;
        DireccionPostal nuevaDir;

        String seleccionarPersonaEmpresa = request.queryParams("seleccionarPersonaEmpresa");

        switch (seleccionarPersonaEmpresa){
            case "Persona":
                String apellido = request.queryParams("apellido");
                String nombre = request.queryParams("nombre");
                String dni = request.queryParams("dni");
                calle = request.queryParams("calle");
                numero = request.queryParams("numero");
                piso = request.queryParams("piso");
                departamento = request.queryParams("departamento");
                pais = request.queryParams("pais");
                provincia = request.queryParams("provincia");
                ciudad = request.queryParams("ciudad");

               nuevaDir = ControllerDirecciones.generarDireccion(calle,numero,piso,departamento,pais,provincia,ciudad);

               Proveedor persona = new Proveedor(nombre, apellido, dni, nuevaDir);

               persistirPersona(persona);

                break;
            case "Empresa":
                String razonSocial = request.queryParams("razonSocial");
                String cuilCuit = request.queryParams("cuilCuit");
                calle = request.queryParams("calleEmp");
                numero = request.queryParams("numeroEmp");
                piso = request.queryParams("pisoEmp");
                departamento = request.queryParams("departamentoEmp");
                pais = request.queryParams("paisEmp");
                provincia = request.queryParams("provinciaEmp");
                ciudad = request.queryParams("ciudadEmp");

                nuevaDir = ControllerDirecciones.generarDireccion(calle,numero,piso,departamento,pais,provincia,ciudad);

                Proveedor empresa = new Proveedor(razonSocial, cuilCuit, nuevaDir);

                persistirEmpresa(empresa);

                break;
            default:
                //Error: el usuario seleccionó cualquier cosa

        }



        response.redirect("cargar_proveedor");

        return null;
    }

    public static void persistirEmpresa(Proveedor empresa){

        DAO DAOProvEmpresa = new DAOBBDD<Proveedor>(Proveedor.class); //dao generico de BBDD
        Repositorio repoProvEmpresa = new Repositorio<Proveedor>(DAOProvEmpresa); //repositorio que tambien usa generics

        if(!repoProvEmpresa.existe(empresa))
            repoProvEmpresa.agregar(empresa);
    }

    public static void persistirPersona(Proveedor persona){

        DAO DAOProvPersona = new DAOBBDD<Proveedor>(Proveedor.class); //dao generico de BBDD
        Repositorio repoProvPersona = new Repositorio<Proveedor>(DAOProvPersona); //repositorio que tambien usa generics

        if(!repoProvPersona.existe(persona))
            repoProvPersona.agregar(persona);

    }
}

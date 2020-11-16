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


        String rs=request.queryParamOrDefault("razonSocial","Ingrese razon social");
        String rf=request.queryParamOrDefault("nombreFicticio","Ingrese nombre ficticio");
        String cuit=request.queryParamOrDefault("cuilOCuit","Ingrese  CUIL o CUIT");
        String calle=request.queryParamOrDefault("calle","Ingrese calle");
        String piso=request.queryParamOrDefault("piso","piso");
        String numero=request.queryParamOrDefault("numeroCalle","nro");
        String depto=request.queryParamOrDefault("dpto","depto");

        String apellido=request.queryParamOrDefault("Apellido","Ingrese apellido");
        String nombre=request.queryParamOrDefault("nombreFicticio","Ingrese nombre");
        String dni=request.queryParamOrDefault("DNI","Ingrese  DNI");
        String callePers=request.queryParamOrDefault("callePers","Ingrese calle");
        String pisoPers=request.queryParamOrDefault("pisoPers","piso");
        String numeroPers=request.queryParamOrDefault("numeroPers","nro");
        String deptoPers=request.queryParamOrDefault("deptoPers","depto");


        datos.put("paises",paises);
        datos.put("esEmpresa",esEmpresa);
        datos.put("razonSocialDefault",rs);
        datos.put("nombreFicticioDefault",rf);
        datos.put("cuitDefault",cuit);
        datos.put("calleDefault",calle);
        datos.put("pisoDefault",piso);
        datos.put("deptoDefault",depto);
        datos.put("numeroDefault",numero);

        datos.put("esPersona",esPersona);
        datos.put("apellido",apellido);
        datos.put("nombre",nombre);
        datos.put("dni",dni);
        datos.put("callePers",callePers);
        datos.put("pisoPers",pisoPers);
        datos.put("deptoPers",deptoPers);
        datos.put("numeroPers",numeroPers);





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

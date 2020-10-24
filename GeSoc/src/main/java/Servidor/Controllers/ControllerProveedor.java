package Servidor.Controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ControllerProveedor {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "cargar_proveedor.html");

        return vista;
    }

    public static Object cargarProveedor(Request request, Response response) {

        String calle, numero, piso, departamento, pais, provincia, ciudad;

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

                System.out.println(seleccionarPersonaEmpresa);
                System.out.println(apellido);
                System.out.println(nombre);
                System.out.println(dni);
                System.out.println(calle);
                System.out.println(numero);
                System.out.println(piso);
                System.out.println(departamento);
                System.out.println(pais);
                System.out.println(provincia);
                System.out.println(ciudad);

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

                System.out.println(seleccionarPersonaEmpresa);
                System.out.println(razonSocial);
                System.out.println(cuilCuit);
                System.out.println(calle);
                System.out.println(numero);
                System.out.println(piso);
                System.out.println(departamento);
                System.out.println(pais);
                System.out.println(provincia);
                System.out.println(ciudad);

                break;
            default:
                //Error: el usuario seleccionó cualquier cosa

        }



        response.redirect("pantalla_principal_usuario");

        return null;
    }
}

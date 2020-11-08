package Servidor.Controllers;

import Dominio.Egreso.Core.*;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerEgresos {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "cargar_egreso.html");

        return vista;
    }

    public static Object cargarEgreso(Request request, Response response) {

        String entidad = request.queryParams("seleccionarEntidad");
        String fecha = request.queryParams("fecha");
        String metodoPago = request.queryParams("seleccionarMetodoPago");
        String proveedor = request.queryParams("proveedor");
        String documentoComercial = request.queryParams("documentoComercial");
        String descripcionDocComercial = request.queryParams("descripcionDocComercial");

        Egreso egreso = new Egreso(LocalDate.parse(fecha), "Uruguay", 0, new ArrayList<>(), new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO, "TD"), new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.REMITO, descripcionDocComercial), null);

        //PROBLEMAS!


        if(request.queryParams("esRevisor")!=null){

            Usuario usuario = ControllerSesion.obtenerUsuariodeSesion(request);
            usuario.set

            //Es revisor TODO
            //Tengo que asignarle el rol al usuario y meterle al rol revisor este egreso
            //Luego persistir el usuario modificado en la db
            //Estaria copado hacer que todos los usuarios arranquen con la lista del rol revisor vacia y cuando
            //van revisando egresos, esta lista se va llenando. Creo que nos va a ahorrar mucho laburo.
        }

        persistirEgreso(egreso);


        response.redirect("cargar_egreso"); //Voy a tener que redireccionar a la pagina de cargar items

        return null;
    }

    public static void persistirEgreso(Egreso egreso){

        DAO DAOEgreso = new DAOBBDD<Egreso>(); //dao generico de BBDD
        Repositorio repoEgreso = new Repositorio<Egreso>(DAOEgreso); //repositorio que tambien usa generics

        if(!repoEgreso.existe(egreso))
            repoEgreso.agregar(egreso);

    }

    public static Object cargarItem(Request request, Response response){
      /*
        String item = request.queryParams("item");
        String valorItem = request.queryParams("valor");

        Item item = new Item(bla bla);

        Egreso egreso = meTraigoElEgreso();

        egreso.agregarItem(item);
      */

        response.redirect("cargar_egreso"); //Reloadeo

        return null;
    }
}

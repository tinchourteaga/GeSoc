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
import java.util.List;
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

        //TODO
        Egreso egreso = new Egreso(LocalDate.parse(fecha), "Uruguay", 0, new ArrayList<>(), new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO, "TD"), new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.REMITO, descripcionDocComercial), null);

        if(request.queryParams("esRevisor")!=null){

            Usuario usuario = ControllerSesion.obtenerUsuariodeSesion(request);
            Usuario usuarioModificado = ControllerSesion.obtenerUsuariodeSesion(request);

            usuarioModificado.getRol().agregarEgresoARevisar(egreso);

            DAO DAOUsuario = new DAOBBDD<Usuario>(); //dao generico de BBDD
            Repositorio repoUsuario = new Repositorio<Usuario>(DAOUsuario);//repositorio que tambien usa generics

            repoUsuario.modificar(usuario, usuarioModificado);
        }

        persistirEgreso(egreso);

        response.redirect("cargar_items?egreso="+egreso.getEgreso());

        return null;
    }

    public static void persistirEgreso(Egreso egreso){

        DAO DAOEgreso = new DAOBBDD<Egreso>(); //dao generico de BBDD
        Repositorio repoEgreso = new Repositorio<Egreso>(DAOEgreso); //repositorio que tambien usa generics

        if(!repoEgreso.existe(egreso))
            repoEgreso.agregar(egreso);

    }

    public static Object cargarItem(Request request, Response response){

        String egreso = request.queryParams("egreso");
        String item = request.queryParams("item");
        String valorItem = request.queryParams("valor");

        Integer egresoId = Integer.valueOf(egreso);
        Float valor = Float.valueOf(valorItem);

        DAO DAOEgreso = new DAOBBDD<Egreso>(Egreso.class); //dao generico de BBDD
        Repositorio repoEgreso = new Repositorio<Egreso>(DAOEgreso); //repositorio que tambien usa generics

        List<Egreso> egresos = repoEgreso.getTodosLosElementos();

        int i = egresos.indexOf(egresoId);

        Egreso objEgreso = egresos.get(i);

        Item objItem = new Item(valor,item,1); //quiero persistir todos de un saque

        objEgreso.agregarItem(objItem);

        persistirEgreso(objEgreso);

        response.redirect("cargar_egreso");

        return null;
    }
}

package Servidor.Controllers;

import Dominio.Egreso.Core.DocumentoComercial;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Core.TipoDocumentoComercial;
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

public class ControllerPresupuesto {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        String funciono = request.queryParams("Exito");
        int valor=0;
        if(funciono!=null){
            valor=Integer.valueOf(funciono).intValue();
        }

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);

        //List<String> monedas= Arrays.asList("Dolar", "Peso argentino", "Euro");
        List<String> documentos=Arrays.asList("Remito","Nota debito","Nota credito","Factura A","Factura B","Factura C","Ticket");
        Repositorio repoProveedores= new Repositorio(new DAOBBDD<Proveedor>(Proveedor.class));
        List<Proveedor> proveedores=repoProveedores.getTodosLosElementos();
        List<Presupuesto> egresos= miUsuario.getEgresosAREvisar().stream().map(e->e.getPresupuestosAConsiderar()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());

        Map<String,Object> datos = new HashMap<>();
        datos.put("documentos",documentos);
        datos.put("proveedor",proveedores);
        datos.put("egreso",egresos);
        datos.put("Exito",valor);//tiene "2 si anduvo, 1 si pincho o 0 si es la primera vez que entra"
        ModelAndView vista = new ModelAndView(datos, "cargar_presupuesto.html");

        return vista;
    }

    public static Object cargarPresupuesto(Request request, Response response) {

        String entidad = request.queryParams("entidad");
        String fecha = request.queryParams("fecha");
        String moneda = request.queryParams("moneda");
        String item = request.queryParams("item");
        String valorItem = request.queryParams("valor");
        String proveedor = request.queryParams("proveedor");
        String documentoComercial = request.queryParams("documentoComercial");
        String linkComprobante = request.queryParams("linkComprobante");

        String archivo=  request.queryParams("file");


        System.out.println(archivo);

        TipoDocumentoComercial tipoDoc;
        switch (documentoComercial){

            case "Ticket":
                tipoDoc=TipoDocumentoComercial.TICKET;
                break;
            case "Remito":
                tipoDoc=TipoDocumentoComercial.REMITO;
                break;
            case "Nota Credito":
                tipoDoc=TipoDocumentoComercial.NOTA_CREDITO;
                break;
            case "Nota Debito":
                tipoDoc=TipoDocumentoComercial.NOTA_DEBITO;
                break;
            case "Factura A":
                tipoDoc=TipoDocumentoComercial.FACTURA_A;
                break;
            case "Factura B":
                tipoDoc=TipoDocumentoComercial.FACTURA_B;
                break;
            case "Factura C":
                tipoDoc=TipoDocumentoComercial.FACTURA_C;
                break;
            default:
                tipoDoc=TipoDocumentoComercial.SIN_DOCUMENTO;
                break;
        }

        Repositorio repoProveedores= new Repositorio(new DAOBBDD<Proveedor>(Proveedor.class));
        List<Proveedor> proveedoresDisponibles=repoProveedores.getTodosLosElementos();
        proveedoresDisponibles=proveedoresDisponibles.stream().filter(p->p.getNombre().equals(proveedor)).collect(Collectors.toList());
        DocumentoComercial documentoAsociado= new DocumentoComercial(tipoDoc,"");

        if(!proveedoresDisponibles.isEmpty()) {
            Presupuesto presupuesto = new Presupuesto(new ArrayList(), new ArrayList(), documentoAsociado, proveedoresDisponibles.get(0));
            presupuesto.setFecha(LocalDate.parse(fecha));
            persistirPresupuesto(presupuesto);
            response.redirect("cargar_items_presupuesto?Presupuesto="+presupuesto.getPresupuesto()+"&us="+request.session().attribute("idUsuarioActual"));
        }else{
            response.redirect("cargar_presupuesto?Exito=1");
        }

        return null;
    }

    public static void persistirPresupuesto(Presupuesto presupuesto){

        DAO DAOPresupuesto = new DAOBBDD<Presupuesto>(); //dao generico de BBDD
        Repositorio repoPresupuesto = new Repositorio<Presupuesto>(DAOPresupuesto); //repositorio que tambien usa generics

        if(!repoPresupuesto.existe(presupuesto))
            repoPresupuesto.agregar(presupuesto);
    }

    public static ModelAndView visualizarPantallaItems(Request request, Response response) {

        return null;
    }
}

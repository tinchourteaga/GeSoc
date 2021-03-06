package Servidor.Controllers;

import Dominio.Egreso.Core.*;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.QueriesUtiles;
import Persistencia.Repos.Repositorio;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.math.NumberUtils;
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


        List<String> documentos=Arrays.asList("Remito","Cebito","Credito","FacturaA","FacturaB","FacturaC","Ticket");
        Repositorio repoProveedores= new Repositorio(new DAOBBDD<Proveedor>(Proveedor.class));
        List<Proveedor> proveedores=repoProveedores.getTodosLosElementos();
        List<Egreso> egresitos = QueriesUtiles.obtenerEgresosDe(miUsuario.getNickName());
        List<Presupuesto> egresos= egresitos.stream().map(e->QueriesUtiles.obtenerPresupuestosDe(e)).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());


        Map<String,Object> datos = new HashMap<>();
        datos.put("egresos",egresitos);
        datos.put("documentos",documentos);
        datos.put("proveedor",proveedores);
        datos.put("egreso",egresos);
        datos.put("Exito",valor);//tiene "2 si anduvo, 1 si pincho o 0 si es la primera vez que entra"
        ModelAndView vista = new ModelAndView(datos, "cargar_presupuesto.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaDetallePresupuesto(Request request, Response response) {

        Map<String, Object> datos= new HashMap<>();

        Optional<String> presupuesto = Optional.ofNullable(request.queryParams("presupuesto"));
        Optional<String> categoriaOCriterio= Optional.ofNullable(request.queryParams("categoriaFiltro"));
        Optional<String> proveedorFiltro= Optional.ofNullable(request.queryParams("proveedorFiltro"));
        Optional<String> fechaFiltro= Optional.ofNullable(request.queryParams("fechaFiltro"));

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);

        final List<Presupuesto>[] misPresupuestos = new List[]{new ArrayList()};
        QueriesUtiles.obtenerEgresosDe(miUsuario.getNickName()).forEach(e-> misPresupuestos[0].addAll(QueriesUtiles.obtenerPresupuestosDe(e)));

        datos.put("categorias", misPresupuestos[0].stream().map(p->QueriesUtiles.obtenerCategoriaDe(p)).flatMap(List::stream).collect(Collectors.toList()));

        List<Proveedor> proveedoresAMostrar=misPresupuestos[0].stream().map(presu->QueriesUtiles.obtenerProveedorDe(presu)).collect(Collectors.toList());

        datos.put("proveedores",proveedoresAMostrar.stream().filter(ControllerPredicado.distinctByKey(Proveedor::getProveedor)).collect(Collectors.toList()));//TODO esto es MAGIC MACI casi

        final List<Presupuesto>[] presupuestosPosibles= new List[]{new ArrayList()};
        final int[] presuId = {0};

        presupuesto.filter(idString-> NumberUtils.isNumber(idString)).ifPresent(pres-> {
            presupuestosPosibles[0] = misPresupuestos[0].stream().filter(pre -> pre.getPresupuesto() == Integer.valueOf(pres).intValue()).collect(Collectors.toList());});
        presupuestosPosibles[0].stream().findFirst().ifPresent(presu->{
            datos.put("presupuesto", presu);
            QueriesUtiles.obtenerEgresosPactadoDeParaPresupuesto(presu,miUsuario.getNickName()).ifPresent(egresoPactadoPresente->datos.put("egresoPactado",egresoPactadoPresente));
            datos.put("detalles",QueriesUtiles.obtenerDetallesDe(presu));
        });


       categoriaOCriterio.filter(crit-> NumberUtils.isNumber(crit)).ifPresent(criterioString->{
           misPresupuestos[0]= misPresupuestos[0].stream().filter(pre->QueriesUtiles.obtenerCategoriaDe(pre).stream().anyMatch(cri->cri.getCategoria()==Integer.valueOf(criterioString))).collect(Collectors.toList());
           Optional<Presupuesto> unPresu= misPresupuestos[0].stream().findFirst();
           unPresu.ifPresent(pre->datos.put("categoriaFiltro",QueriesUtiles.obtenerCategoriaPorPK(Integer.valueOf(criterioString))));
        });
        proveedorFiltro.filter(prov-> NumberUtils.isNumber(prov)).ifPresent(idStringProv->{
            misPresupuestos[0]= misPresupuestos[0].stream().filter(pre->QueriesUtiles.obtenerProveedorDe(pre).getProveedor()==Integer.valueOf(idStringProv)).collect(Collectors.toList());
            Optional<Presupuesto> unPresu= misPresupuestos[0].stream().findFirst();
            unPresu.ifPresent(pre->datos.put("proveedorFiltro",QueriesUtiles.obtenerProveedorDe(pre)));
        });
        datos.put("presupuestos", misPresupuestos[0]);
        return new ModelAndView(datos, "detalle_presupuesto.html");
    }

    public static Object cargarPresupuesto(Request request, Response response) {


        String fecha = request.queryParams("fecha");
        String proveedor = request.queryParams("proveedor");
        String documentoComercial = request.queryParams("documentoComercial");
        String linkComprobante = request.queryParams("linkComprobante");
        String egreso = request.queryParams("egreso");

        System.out.println(request.queryParams());
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
            case "Credito":
                tipoDoc=TipoDocumentoComercial.NOTA_CREDITO;
                break;
            case "Cebito":
                tipoDoc=TipoDocumentoComercial.NOTA_DEBITO;
                break;
            case "FacturaA":
                tipoDoc=TipoDocumentoComercial.FACTURA_A;
                break;
            case "FacturaB":
                tipoDoc=TipoDocumentoComercial.FACTURA_B;
                break;
            case "FacturaC":
                tipoDoc=TipoDocumentoComercial.FACTURA_C;
                break;
            default:
                tipoDoc=TipoDocumentoComercial.SIN_DOCUMENTO;
                break;
        }


        Repositorio repoProveedores= new Repositorio(new DAOBBDD<Proveedor>(Proveedor.class));
        List<Proveedor> proveedoresDisponibles=repoProveedores.getTodosLosElementos();
        DocumentoComercial documentoAsociado= new DocumentoComercial(tipoDoc,"");
        proveedoresDisponibles=proveedoresDisponibles.stream().filter(p->p.getProveedor()== Integer.valueOf(proveedor).intValue()).collect(Collectors.toList());


        if(!proveedoresDisponibles.isEmpty()) {
            Presupuesto presupuesto = new Presupuesto(new ArrayList(), new ArrayList(), documentoAsociado, proveedoresDisponibles.get(0));
            presupuesto.setFecha(LocalDate.parse(fecha));
            presupuesto.setDescripcion(request.queryParams("descripcionPresupuesto"));

            Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
            Integer egresoId = Integer.valueOf(egreso);
            List<Egreso> egresosPosibles = QueriesUtiles.obtenerEgresosDe(miUsuario.getNickName()).stream().filter(e->e.getEgreso()==egresoId.intValue()).collect(Collectors.toList());
            egresosPosibles.stream().findFirst().ifPresent(egresoPosible->{
                egresoPosible.agregarPresupuestoAConsiderar(presupuesto);
                presupuesto.setEgreso(egresoPosible);
            });


            persistirPresupuesto(presupuesto);
            response.redirect("cargar_items_presupuestos?Presupuesto="+presupuesto.getPresupuesto()+"&us="+request.session().attribute("idUsuarioActual"));
        }else{
            response.redirect("cargar_presupuesto?Exito=1");
        }

        return null;
    }

    public static void persistirPresupuesto(Presupuesto presupuesto){

        DAO DAOPresupuesto = new DAOBBDD<Presupuesto>(Presupuesto.class); //dao generico de BBDD
        Repositorio repoPresupuesto = new Repositorio<Presupuesto>(DAOPresupuesto); //repositorio que tambien usa generics

        if(presupuesto.getPresupuesto()==0) {
            repoPresupuesto.agregar(presupuesto);
        }else{
            repoPresupuesto.modificar(null,presupuesto);
        }
    }

    public static ModelAndView visualizarPantallaItems(Request request, Response response) {

        Map<String, Object> datos= new HashMap<>();
        String presupeustoId=  request.queryParams("Presupuesto");

        Repositorio repoPresu=new Repositorio(new DAOBBDD<Presupuesto>(Presupuesto.class));
        List<Presupuesto> presupeustos= repoPresu.getTodosLosElementos();
        List<Presupuesto> presupuestosPosibles= presupeustos.stream().filter(p->p.getPresupuesto()==Integer.valueOf(presupeustoId).intValue()).collect(Collectors.toList());

        Optional<Presupuesto> presupuestofinal=QueriesUtiles.obtenerPresupeustoPorPK(presupeustoId);
        if(presupuestofinal.isPresent()){
            datos.put("presupuesto", presupuestofinal.get());
            return new ModelAndView(datos, "cargar_items_presupuestos.html");
        }
        response.redirect("pantalla_principal_usuario?pre="+presupeustoId);
        return null;
    }

    public static Object cargarItem(Request request, Response response) {


        String egreso = request.queryParams("boton_carga_items");
        Integer egresoId = Integer.valueOf(egreso);

        DAO DAOEgreso = new DAOBBDD<Presupuesto>(Presupuesto.class);
        Repositorio repoEgreso = new Repositorio<Presupuesto>(DAOEgreso);

        List<Presupuesto> presupuestos = repoEgreso.getTodosLosElementos();

        List<Presupuesto> presupuestoList = presupuestos.stream().filter(e -> e.getPresupuesto() == egresoId.intValue()).collect(Collectors.toList());

        if(presupuestoList.isEmpty()){
            return null;
        }

        Presupuesto presupuestoObj = presupuestoList.get(0);

        if (request.queryParams("json") != null && !request.queryParams("json").isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonArray responseObj = parser.parse(request.queryParams("json")).getAsJsonArray();

            Repositorio repoDetalle= new Repositorio(new DAOBBDD<Detalle>(Detalle.class));
            responseObj.forEach(jsonElement ->{
                Detalle objItem = new Detalle(jsonElement.getAsJsonObject().get("precio").getAsFloat(), jsonElement.getAsJsonObject().get("nombre").getAsString(), jsonElement.getAsJsonObject().get("cantidad").getAsInt());
                presupuestoObj.getDetalles().add(objItem);
                objItem.setPresupuesto(presupuestoObj);
                repoDetalle.agregar(objItem);
            });
            presupuestoObj.recalcularValor();
        }
        persistirPresupuesto(presupuestoObj);
        System.out.println(request.queryParams("form_json"));
        response.redirect("cargar_presupuesto");


        return null;
    }
}

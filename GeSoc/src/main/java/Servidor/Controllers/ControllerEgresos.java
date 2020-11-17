package Servidor.Controllers;

import Dominio.BandejaMensajes.Mensaje;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioMenorPrecio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Egreso.Core.*;
import Dominio.Egreso.Validador.EstrategiasRevision.EjecucionAutomatica;
import Dominio.Egreso.Validador.ValidadorDeOperacion;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ControllerEgresos {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);

        String funciono = request.queryParams("Exito");
        int valor=0;
        if(funciono!=null){
            valor=Integer.valueOf(funciono).intValue();
        }

        Map<String,Object> datos = new HashMap<>();
        List<String> criterios= Arrays.asList("Menor Precio");
        List<String> documentos=Arrays.asList("Remito","Nota debito","Nota credito","Factura A","Factura B","Factura C","Ticket");
        List<String> metodos= Arrays.asList("Tarjeta de credito","Tarjeta de debito","Efectivo","Cheque");
        List<Egreso> egresos= miUsuario.getEgresosAREvisar();

        datos.put("criterios",criterios);
        datos.put("documentos",documentos);
        datos.put("metodos",metodos);
        datos.put("egreso",egresos);
        datos.put("Exito",valor);//tiene "2 si anduvo, 1 si pincho o 0 si es la primera vez que entra
        ModelAndView vista = new ModelAndView(datos, "cargar_egreso.html");

        return vista;
    }

    public static Object cargarEgreso(Request request, Response response) {


        String fecha = request.queryParams("fecha");
        String pais = request.queryParams("pais");
        String metodoPago = request.queryParams("seleccionarMetodoPago");
        String criterioString = request.queryParams("criterio");
        String documentoComercial = request.queryParams("documentoComercial");
        String descripcionDocComercial = request.queryParams("descripcionDocComercial");

        DocumentoComercial documentoAsociado;
        CriterioSeleccionProveedor criterio;
        MetodoDePago medioDePago=null;

        switch(criterioString){

            case "Menor Precio":
                criterio= new CriterioMenorPrecio();
                break;
            default:
                criterio=null;
                break;
        }

        switch(metodoPago){

            case"Cheque":
                medioDePago=new MetodoDePago(TipoDeMedioDePago.CHEQUE,"Pago registrado el "+LocalDate.now().toString());
                break;

            case"Efectivo":
                medioDePago=new MetodoDePago(TipoDeMedioDePago.EFECTIVO,"Pago registrado el "+LocalDate.now().toString());
                break;


            case"Tarjeta de credito":
                medioDePago=new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO,"Pago registrado el "+LocalDate.now().toString());
                break;

            case"Tarjeta de debito":
                medioDePago=new MetodoDePago(TipoDeMedioDePago.TARJETA_DEBITO,"Pago registrado el "+LocalDate.now().toString());
                break;

        }

        switch (documentoComercial){

            case "Remito":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.REMITO,descripcionDocComercial);
                break;
            case "Ticket":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.TICKET,descripcionDocComercial);
                break;
            case "Nota credito":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.NOTA_CREDITO,descripcionDocComercial);
                break;
            case "Nota debito":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.NOTA_DEBITO,descripcionDocComercial);
                break;
            case "Factura A":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.FACTURA_A,descripcionDocComercial);
                break;
            case "Factura B":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.FACTURA_B,descripcionDocComercial);
                break;
            case "Factura C":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.FACTURA_C,descripcionDocComercial);
                break;

            default:
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,descripcionDocComercial);
                break;
        }

       //if(medioDePago==null || documentoAsociado==null /*|| criterio==null*/){
        //    response.redirect("cargar_egreso?Exito=1");
       // }

        Egreso egreso = new Egreso(LocalDate.parse(fecha), pais, new ArrayList<>(), medioDePago, new ArrayList<>(), documentoAsociado, criterio);

        if(request.queryParams("esRevisor")!=null){

            Usuario usuario = ControllerSesion.obtenerUsuariodeSesion(request);
            Usuario usuarioModificado = ControllerSesion.obtenerUsuariodeSesion(request);

            usuarioModificado.agregarEgresoARevisar(egreso);

            DAO DAOUsuario = new DAOBBDD<Usuario>();
            Repositorio repoUsuario = new Repositorio<Usuario>(DAOUsuario);
            repoUsuario.modificar(usuario, usuarioModificado);
        }

        persistirEgreso(egreso);

        //para que no se pase de vivo y no modifique cosas que no deberia el weon
        response.redirect("cargar_items_egreso?egreso="+egreso.getEgreso()+"&us="+request.session().attribute("idUsuarioActual"));

        return null;
    }

    public static void persistirEgreso(Egreso egreso){

        DAO DAOEgreso = new DAOBBDD<Egreso>(Egreso.class); //dao generico de BBDD
        Repositorio repoEgreso = new Repositorio<Egreso>(DAOEgreso); //repositorio que tambien usa generics

        if(!repoEgreso.existe(egreso))
            repoEgreso.agregar(egreso);

    }

    public static Object cargarItem(Request request, Response response) throws IOException {

        /*
        RequestMaker rm = RequestMaker.getInstance();

        rm.crearGET("http://localhost:63342/cargar_items_egresos");
        */

        String egreso = request.queryParams("egreso");
        String nombre = request.queryParams("nombreItem");
        String tipo = request.queryParams("tipo");
        String cantidad = request.queryParams("cant");
        String precio = request.queryParams("precio");

        Integer egresoId = Integer.valueOf(egreso);
        Integer cant = Integer.valueOf(cantidad);
        Float valor = Float.valueOf(precio);

        DAO DAOEgreso = new DAOBBDD<Egreso>(Egreso.class);
        Repositorio repoEgreso = new Repositorio<Egreso>(DAOEgreso);

        List<Egreso> egresos = repoEgreso.getTodosLosElementos();

        List<Egreso> egresosList = egresos.stream().filter(e -> e.getEgreso() == egresoId.intValue()).collect(Collectors.toList());

        if(egresosList.isEmpty()){
            return null;
        }

        Egreso objEgreso = egresosList.get(0);

        Item objItem = new Item(valor,nombre,cant);
        objItem.setTipo(tipo);

        objEgreso.agregarItem(objItem);

        persistirEgreso(objEgreso);

        response.redirect("cargar_egreso");

        return null;
    }

    public static ModelAndView visualizarPantallaItems(Request request, Response response) {
        // reviso que el sea el mismo usuario que cargo el egreso
        String idUS= request.queryParams("us");
        String egresoId= request.queryParams("egreso");
        if(!idUS.equals(request.session().attribute("idUsuarioActual"))){
            //a tu casa crack, no podes cargar items de cosas que no te corresponde
            response.redirect("/pantalla_principal_usuario");
        }

        Map<String,Object> datos= new HashMap<String, Object>();
        Egreso egreso=null;
        Repositorio repoEgresos= new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
        List<Egreso>egresos= repoEgresos.getTodosLosElementos();
        egresos= egresos.stream().filter(e->e.getEgreso()== Integer.valueOf(egresoId).intValue()).collect(Collectors.toList());
        if(egresos.isEmpty()){
            response.redirect("/pantalla_principal_usuario");
            return null;
        }
        egreso=egresos.get(0);
        datos.put("egreso",egreso);
        return new ModelAndView(datos, "cargar_items_egresos.html");
    }

    public static ModelAndView visualizarPantallaDetalleEgreso(Request request, Response response) {

        Map<String, Object> datos= new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "detalle_egreso.html");

        Repositorio repoEgresos = new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
        List<Egreso> egresos = repoEgresos.getTodosLosElementos();

        Repositorio repoProveedores = new Repositorio(new DAOBBDD<Proveedor>(Proveedor.class));
        List<Egreso> proveedores = repoProveedores.getTodosLosElementos();

        Repositorio repoCategorias = new Repositorio(new DAOBBDD<CategoriaCriterio>(CategoriaCriterio.class));
        List<Egreso> categorias = repoCategorias.getTodosLosElementos();

        datos.put("egreso",egresos);
        datos.put("proveedor",proveedores);
        datos.put("categoria",categorias);

        return vista;
    }

    public static ModelAndView visualizarPantallaValidacion(Request request, Response response) {

        Map<String, Object> datos= new HashMap<>();
        Usuario miUsuario=ControllerSesion.obtenerUsuariodeSesion(request);
        List<Egreso> egresosARevisar=miUsuario.getEgresosAREvisar().stream().filter(e->!e.isEstaVerificada()).collect(Collectors.toList());
        datos.put("egreso",egresosARevisar);
        return new ModelAndView(datos, "validacion_egresos.html");
    }

    public static ModelAndView visualizarPantallaValidacionManual(Request request, Response response) {
        Map<String, Object> datos= new HashMap<>();


        String idUS= request.queryParams("us");
        String egresoId=request.queryParams("eg");
        if(!idUS.equals(request.session().attribute("idUsuarioActual"))){
            //a tu casa crack, no podes cargar items de cosas que no te corresponde
            response.redirect("/pantalla_principal_usuario");
            return null;
        }
        Egreso egreso=null;
        Repositorio repoEgresos= new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
        List<Egreso>egresos= repoEgresos.getTodosLosElementos();
        egresos= egresos.stream().filter(e->e.getEgreso()== Integer.valueOf(egresoId).intValue()).collect(Collectors.toList());
        if(egresos.isEmpty()){
            response.redirect("/pantalla_principal_usuario");
            return null;
        }
        egreso=egresos.get(0);
        datos.put("egreso",egreso);
        datos.put("presupuestos",egreso.getPresupuestosAConsiderar());

        if(egreso.getPresupuestoPactado()!=null){
            datos.put("tienePresupuesto","true");
            datos.put("presupuesto",egreso.getPresupuestoPactado());

        }
        /*
        Repositorio repoItems=new Repositorio(new DAODDBB<Item>(Item.class));
        List<Item> items= repoItems.getTodosLosElementos();
        List<Item> itemsAMostrar= items.stream().filter(i->i.getEgreso().getEgreso()==Integer.valueOf(egresoId).intValue()).Collect(Collectors.toList());
        datos.put("listaItems",itemsAMostrar);
        * */

        datos.put("listaItems",egreso.getListaItems());
        datos.put("categorias",egreso.getCategorias());

        return new ModelAndView(datos, "validacion_manual.html");
    }

    public static Object redireccionarValidacion(Request request, Response response) {

        String estrategia=request.queryParams("estrategia");
        String egreso=request.queryParams("egreso");
        if(estrategia.equals("Selecciona")){
            response.redirect("/validar_egresos");
            return null;
        }
        if(estrategia.equals("Manual")){
            response.redirect("/validar_egreso_manualmente?eg="+egreso+"&us="+request.session().attribute("idUsuarioActual"));
            return null;
        }
        if(estrategia.equals("Automática")){
            String hora=request.queryParams("hora");
            String minuto= request.queryParams("minutos");
            int horaEjecucion=Integer.valueOf(hora).intValue();
            int minutoEjecucion=Integer.valueOf(minuto).intValue();


            ValidadorDeOperacion.setEstrategia(new EjecucionAutomatica(horaEjecucion,minutoEjecucion));
            Repositorio repoEgreso= new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
            List<Egreso> egresosPosibles= repoEgreso.getTodosLosElementos();
            egresosPosibles=egresosPosibles.stream().filter(e->e.getEgreso()==Integer.valueOf(egreso).intValue()).collect(Collectors.toList());
            if(!egresosPosibles.isEmpty()){
                response.redirect("/validar_egresos");
                return null;
            }else{
                Egreso egresoObj=egresosPosibles.get(0);
                ValidadorDeOperacion.validarPorEstrategia(egresoObj);
                response.redirect("/pantalla_principal_usuario");
                return null;
            }
        }


        response.redirect("/pantalla_principal_usuario");
        return null;
    }

    public static Object rechazarEgreso(Request request, Response response) {

        String egresoId= request.queryParams("egreso");
        System.out.println("egresoId");

        Repositorio repositorio= new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
        List<Egreso> todosLosEgresos= repositorio.getTodosLosElementos();
        List<Egreso> egresosPosibles= todosLosEgresos.stream().filter(e->e.getEgreso()==Integer.valueOf(egresoId).intValue()).collect(Collectors.toList());
        if(egresosPosibles.isEmpty()){
            response.redirect("/pantalla_principal_usuario");
            return null;
        }
        Repositorio repositorioUs= new Repositorio(new DAOBBDD<Usuario>(Usuario.class));
        List<Usuario> todosLosUs= repositorioUs.getTodosLosElementos();
        List<Usuario> revisores= todosLosUs.stream().filter(us->us.getEgresosAREvisar().containsAll(egresosPosibles)).collect(Collectors.toList());
        revisores.forEach(rev-> {
            Repositorio repoMsj=new Repositorio(new DAOBBDD<Mensaje>(Mensaje.class));
            Mensaje msj=new Mensaje(LocalDate.now(), null, "La operacion realizada en fecha " + egresosPosibles.get(0).getFecha() + "y con valor: "+egresosPosibles.get(0).getValor().getImporte() +" no se valido correctamente. Debe validarse nuevamente", egresosPosibles.get(0));
            rev.getBandejaDeMensajes().getMensajes().add(msj);
            msj.setEgreso(egresosPosibles.get(0));
            msj.setUsuario(rev);
            repoMsj.agregar(msj);
        });
        egresosPosibles.get(0).setEstaVerificada(true);
        repositorio.modificar(null,egresosPosibles.get(0));
        revisores.forEach(rev-> repositorioUs.modificar(null,rev));
        response.redirect("/validar_egresos");
        return null;
    }

    public static Object aceptarEgreso(Request request, Response response) {

        String egresoId= request.queryParams("egreso");
        System.out.println(egresoId);

        Repositorio repositorio= new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
        List<Egreso> todosLosEgresos= repositorio.getTodosLosElementos();
        List<Egreso> egresosPosibles= todosLosEgresos.stream().filter(e->e.getEgreso()==Integer.valueOf(egresoId).intValue()).collect(Collectors.toList());
        if(egresosPosibles.isEmpty()){
            response.redirect("/pantalla_principal_usuario");
            return null;
        }
        Repositorio repositorioUs= new Repositorio(new DAOBBDD<Usuario>(Usuario.class));
        List<Usuario> todosLosUs= repositorioUs.getTodosLosElementos();
        List<Usuario> revisores= todosLosUs.stream().filter(us->us.getEgresosAREvisar().containsAll(egresosPosibles)).collect(Collectors.toList());
        revisores.forEach(rev-> {
            Repositorio repoMsj=new Repositorio(new DAOBBDD<Mensaje>(Mensaje.class));
            Mensaje msj=new Mensaje(LocalDate.now(), null, "La operacion realizada en fecha " + egresosPosibles.get(0).getFecha() + "y con valor: "+egresosPosibles.get(0).getValor().getImporte() +" se valido satifactoriamente", egresosPosibles.get(0));
            rev.getBandejaDeMensajes().getMensajes().add(msj);
            msj.setEgreso(egresosPosibles.get(0));
            msj.setUsuario(rev);
            repoMsj.agregar(msj);
        });
        egresosPosibles.get(0).setEstaVerificada(true);
        repositorio.modificar(null,egresosPosibles.get(0));
        revisores.forEach(rev-> repositorioUs.modificar(null,rev));// asi guarda el msj
        response.redirect("/validar_egresos");
        return null;
    }
}

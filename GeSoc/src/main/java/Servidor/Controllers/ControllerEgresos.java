package Servidor.Controllers;

import Dominio.BandejaMensajes.Mensaje;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioMenorPrecio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Egreso.Core.*;
import Dominio.Egreso.Validador.EstrategiasRevision.EjecucionAutomatica;
import Dominio.Egreso.Validador.ValidadorDeOperacion;
import Dominio.Entidad.Entidad;
import Dominio.Ingreso.Ingreso;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
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
        List<String> documentos=Arrays.asList("Remito","debito","credito","FacturaA","FacturaB","FacturaC","Ticket");
        List<String> metodos= Arrays.asList("TarjetaCredito","TarjetaDebito","Efectivo","Cheque");
        List<Egreso> egresos= miUsuario.getEgresosAREvisar();
        List<Entidad> entidades= egresos.stream().map(e->e.getEntidad()).collect(Collectors.toList());
        Set<Entidad> entidadSet=new HashSet<>();
        entidadSet.addAll(entidades);
        entidades.clear();
        entidades.addAll(entidadSet);

        Entidad entidad = miUsuario.getEntidades().get(0);

        Repositorio repoEgreso = new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
        List<Egreso> egresosPosibles = repoEgreso.getTodosLosElementos();
        List<Egreso> egresosTabla = egresosPosibles.stream().filter(e -> e.getEntidad().equals(entidad)).collect(Collectors.toList());

        datos.put("entidad",entidades);
        datos.put("criterios",criterios);
        datos.put("documentos",documentos);
        datos.put("metodos",metodos);
        datos.put("egreso",egresos);
        datos.put("Exito",valor);//tiene "2 si anduvo, 1 si pincho o 0 si es la primera vez que entra

        datos.put("egresoTabla", egresosTabla);
        ModelAndView vista = new ModelAndView(datos, "cargar_egreso.html");

        return vista;
    }

    public static Object cargarEgreso(Request request, Response response) {


        String fecha = String.format(request.queryParams("fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String pais = request.queryParams("pais");
        String metodoPago = request.queryParams("seleccionarMetodoPago");
        String criterioString = request.queryParams("criterio");
        String documentoComercial = request.queryParams("documentoComercial");
        String descripcionDocComercial = request.queryParams("descripcionDocComercial");
        String descripcionEgreso = request.queryParams("descripcionEgreso");
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


            case"TarjetaCredito":
                medioDePago=new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO,"Pago registrado el "+LocalDate.now().toString());
                break;

            case"TarjetaDebito":
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
            case "credito":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.NOTA_CREDITO,descripcionDocComercial);
                break;
            case "debito":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.NOTA_DEBITO,descripcionDocComercial);
                break;
            case "FacturaA":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.FACTURA_A,descripcionDocComercial);
                break;
            case "FacturaB":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.FACTURA_B,descripcionDocComercial);
                break;
            case "FacturaC":
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.FACTURA_C,descripcionDocComercial);
                break;

            default:
                documentoAsociado=new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,descripcionDocComercial);
                break;
        }

       //if(medioDePago==null || documentoAsociado==null /*|| criterio==null*/){
        //    response.redirect("cargar_egreso?Exito=1");
       // }

        Egreso egreso = new Egreso(LocalDate.parse(fecha), pais, new ArrayList<>(), medioDePago, new ArrayList<>(), documentoAsociado,  new CriterioMenorPrecio());

        String entidadId=request.queryParams("entidad");

        Repositorio repoEntidades= new Repositorio(new DAOBBDD<Entidad>(Entidad.class));
        List<Entidad> entidades= repoEntidades.getTodosLosElementos();
        List<Entidad> entidadesPosibles= entidades.stream().filter(e->e.getEntidad()==Integer.valueOf(entidadId).intValue()).collect(Collectors.toList());
        egreso.setDescripcion(descripcionEgreso);

        if(!entidadesPosibles.isEmpty())
        egreso.setEntidad(entidadesPosibles.get(0));

        Usuario usuario = ControllerSesion.obtenerUsuariodeSesion(request);
        Usuario usuarioModificado = ControllerSesion.obtenerUsuariodeSesion(request);

        usuarioModificado.agregarEgresoARevisar(egreso);

        DAO DAOUsuario = new DAOBBDD<Usuario>();
        Repositorio repoUsuario = new Repositorio<Usuario>(DAOUsuario);
        repoUsuario.modificar(usuario, usuarioModificado);


        persistirEgreso(egreso);


        //para que no se pase de vivo y no modifique cosas que no deberia el weon
        response.redirect("cargar_items_egreso?egreso="+egreso.getEgreso()+"&us="+request.session().attribute("idUsuarioActual"));

        return null;
    }

    public static void persistirEgreso(Egreso egreso){

        DAO DAOEgreso = new DAOBBDD<Egreso>(Egreso.class); //dao generico de BBDD
        Repositorio repoEgreso = new Repositorio<Egreso>(DAOEgreso); //repositorio que tambien usa generics

        if(egreso.getEgreso()==0) {
            repoEgreso.agregar(egreso);
        }else{
            repoEgreso.modificar(null,egreso);
        }

    }

    public static Object cargarItem(Request request, Response response) throws IOException {

        /*
        RequestMaker rm = RequestMaker.getInstance();

        rm.crearGET("http://localhost:63342/cargar_items_egresos");
        */

        System.out.println(request.queryParams());
        System.out.println(request.queryParams("form_json"));
        String egreso = request.queryParams("boton_carga_items");


        Integer egresoId = Integer.valueOf(egreso);

        DAO DAOEgreso = new DAOBBDD<Egreso>(Egreso.class);
        Repositorio repoEgreso = new Repositorio<Egreso>(DAOEgreso);

        List<Egreso> egresos = repoEgreso.getTodosLosElementos();

        List<Egreso> egresosList = egresos.stream().filter(e -> e.getEgreso() == egresoId.intValue()).collect(Collectors.toList());

        if(egresosList.isEmpty()){
            return null;
        }

        Egreso objEgreso = egresosList.get(0);

        if (request.queryParams("form_json") != null && !request.queryParams("form_json").isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonArray responseObj = parser.parse(request.queryParams("form_json")).getAsJsonArray();

            List<Item> items=new ArrayList();
            responseObj.forEach(jsonElement -> {
                        Item objItem = new Item(jsonElement.getAsJsonObject().get("precio").getAsFloat(), jsonElement.getAsJsonObject().get("nombre").getAsString(), jsonElement.getAsJsonObject().get("cantidad").getAsInt());
                        objItem.setTipo(jsonElement.getAsJsonObject().get("tipo").getAsString());
                        objEgreso.agregarItem(objItem);
                        objItem.setEgreso(objEgreso);
                items.add(objItem);
            });
            objEgreso.recalcularValor();

        }


        persistirEgreso(objEgreso);
        /*
        Si lo de persisitr egreso no funca borralo y pone:

        DAO DAOEgreso = new DAOBBDD<Egreso>(Egreso.class);
        Repositorio repoEgreso = new Repositorio<Egreso>(DAOEgreso);
        repoEgreso.modificar(null,objEgreso);
        */

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
        Egreso egreso;
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

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        List<Entidad> entidades= miUsuario.getEgresosAREvisar().stream().map(e->e.getEntidad()).collect(Collectors.toList());
        Set<Entidad> entidadSet= new HashSet<>();
        entidadSet.addAll(entidades);
        entidades.clear();
        entidades.addAll(entidadSet);



        Repositorio repoCats =new Repositorio(new DAOBBDD<CategoriaCriterio>(CategoriaCriterio.class));
        List<CategoriaCriterio> categorias = repoCats.getTodosLosElementos();
        categorias= categorias.stream().filter(cat-> entidades.contains(cat.getCriterio().getEntidad())).collect(Collectors.toList());

        Optional<String> egresoId =Optional.ofNullable( request.queryParams("eg"));
        Optional<String> entidadId =Optional.ofNullable( request.queryParams("entElegida"));
        Optional<String> fechaFiltro =Optional.ofNullable( request.queryParams("fechaElegida"));
        Optional<String> catId =Optional.ofNullable( request.queryParams("catElegido"));

        entidadId.filter(id->!id.equals("-")).ifPresent(idString-> {
                    datos.put("entElegida", entidades.stream().filter(ent -> ent.getEntidad() ==Integer.valueOf(idString).intValue()).collect(Collectors.toList()).get(0));
                });

        List<CategoriaCriterio> finalCategorias = categorias;
        catId.filter(id->!id.equals("-")).ifPresent(idCat->{
            datos.put("catElegido", finalCategorias.stream().filter(cat-> cat.getCategoria()==Integer.valueOf(idCat)).collect(Collectors.toList()).get(0));
        });
        datos.put("egreso",miUsuario.getEgresosAREvisar().stream().filter(eg->{

            String idAFiltrar=entidadId.filter(id->!id.equals("-")).orElse(String.valueOf(eg.getEntidad().getEntidad()));

            final AtomicBoolean[] flag = {new AtomicBoolean(eg.getEntidad().getEntidad() == Integer.valueOf(idAFiltrar).intValue())};
            fechaFiltro.filter(val->!val.equals("")).ifPresent(fecha->{
                flag[0].set(flag[0].get() &&  eg.getFecha().toString().equals(fechaFiltro));
            });

            flag[0].set(flag[0].get() && eg.getCategorias().stream().anyMatch(cat -> cat.getCategoria()==Integer.valueOf(catId.filter(id->!id.equals("-")).orElse(String.valueOf(cat.getCategoria())))));

            return flag[0].get();
        }).collect(Collectors.toList()));
        datos.put("entidades",entidades);

        datos.put("categoria",categorias);

        egresoId.filter(id->!id.equals("seleccionEgreso")).ifPresent(egresoIdString->{
            Egreso egreso = miUsuario.getEgresosAREvisar().stream().filter(e -> e.getEgreso() == Integer.valueOf(egresoIdString).intValue()).collect(Collectors.toList()).get(0);

            datos.put("egresoPactado",egreso);
            LocalDate fecha = egreso.getFecha();
            List<Presupuesto> presupuestosConsiderados = egreso.getPresupuestosAConsiderar();
            Presupuesto presupuesto = egreso.getPresupuestoPactado();
            Ingreso ingresoVinculado = egreso.getIngreso();
            MetodoDePago mp = egreso.getMetodoDePago();
            List<CategoriaCriterio> cat = egreso.getCategorias();
            List<Item> items = egreso.getListaItems();
            datos.put("ing",ingresoVinculado);
            datos.put("presele",presupuesto);
            datos.put("cat",cat);
            datos.put("presasc",presupuestosConsiderados);
            datos.put("mp",mp);
            datos.put("entidad",egreso.getEntidad());
            datos.put("fechaSelect",fecha);
            datos.put("items",items);
        });

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
            if(egresosPosibles.isEmpty()){
                response.redirect("/validar_egresos");
                return null;
            }else{
                Egreso egresoObj=egresosPosibles.get(0);
                ValidadorDeOperacion.validarPorEstrategia(egresoObj);
                response.redirect("/validar_egresos");
                return null;
            }
        }


        response.redirect("/validar_egresos");
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
            Mensaje msj=new Mensaje(LocalDate.now(), null, "La operacion realizada en fecha " + egresosPosibles.get(0).getFecha() + " y con valor: "+egresosPosibles.get(0).getValor().getImporte() +" no se valido correctamente. Debe validarse nuevamente", egresosPosibles.get(0));
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
            Mensaje msj=new Mensaje(LocalDate.now(), null, "La operacion realizada en fecha " + egresosPosibles.get(0).getFecha() + " y con valor: "+egresosPosibles.get(0).getValor().getImporte() +" se valido satifactoriamente", egresosPosibles.get(0));
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

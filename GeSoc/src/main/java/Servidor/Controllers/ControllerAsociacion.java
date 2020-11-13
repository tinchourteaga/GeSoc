package Servidor.Controllers;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Egreso.Core.*;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Dominio.Entidad.Entidad;
import Dominio.Entidad.EntidadJuridica;
import Dominio.Entidad.OrganizacionSocial;
import Dominio.Ingreso.Excepciones.NoPuedoAsignarMasDineroDelQueTengoException;
import Dominio.Ingreso.Ingreso;
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

public class ControllerAsociacion {

    public static ModelAndView visualizarPantallaIngresosYEgresos(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();

        //Tests
        List<Egreso> egresos = new ArrayList<>();
        List<Ingreso> ingresos = new ArrayList<>();
        List<Entidad> entidades = new ArrayList<>();

        Egreso egresoPrueba = new Egreso(LocalDate.parse("2014-02-14"), "Uruguay",  Arrays.asList(new Item(8888f,"",1)), new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO, "TD"), new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.REMITO, "datojajaj"), new CriterioSeleccionProveedor() {
            @Override
            public Proveedor seleccionarProveedor(List<Proveedor> proveedores) {
                return null;
            }

            @Override
            public void validar(Egreso operacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {

            }

            @Override
            public Presupuesto seleccionarPresupuesto(List<Presupuesto> presupuestos) {
                return null;
            }
        });

        Egreso egresoPrueba2 = new Egreso(LocalDate.parse("2013-02-14"), "Paraguay", Arrays.asList(new Item(2222f,"",1)),  new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO, "TD"), new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.REMITO, "datos.jajaj"), new CriterioSeleccionProveedor() {
            @Override
            public Proveedor seleccionarProveedor(List<Proveedor> proveedores) {
                return null;
            }

            @Override
            public void validar(Egreso operacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {

            }

            @Override
            public Presupuesto seleccionarPresupuesto(List<Presupuesto> presupuestos) {
                return null;
            }
        });

        Entidad entidadPrueba1 = new EntidadJuridica("entidadPrueba1", "JorgeCampeon",new OrganizacionSocial());
        entidadPrueba1.setEntidad(57);

        Entidad entidadPrueba2 = new EntidadJuridica("entidadPrueba2", "JuanCampeon",new OrganizacionSocial());
        entidadPrueba2.setEntidad(37);

        entidades.add(entidadPrueba1);
        entidades.add(entidadPrueba2);

        egresoPrueba.setEntidad(entidadPrueba1);
        egresoPrueba2.setEntidad(entidadPrueba1);

        egresos.add(egresoPrueba);
        egresos.add(egresoPrueba2);

        Ingreso ingresoPrueba = new Ingreso("$",230,LocalDate.of(2020,10,14),LocalDate.now(),"descrip",new ArrayList<>());
        Ingreso ingresoPrueba2 = new Ingreso("$",10,LocalDate.of(2020,10,14),LocalDate.now(),"descrip2",new ArrayList<>());

        egresoPrueba.setIngreso(ingresoPrueba);
        egresoPrueba2.setIngreso(ingresoPrueba2);

        datos.put("egreso",egresos);

        ModelAndView vista = new ModelAndView(datos, "asociar_ingresos_y_egresos.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaEgresosYPresupuestos(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);

        //miUsuario.getEntidadesAlaQuePertenece(); en vez de buscar todas las entidades habria que hacerlo con este metodo y gg
        Repositorio repoEntidades= new Repositorio(new DAOBBDD<Entidad>(Entidad.class));
        List<Entidad> entidades = repoEntidades.getTodosLosElementos();


        //Tests
        List<Egreso> egresos = entidades.stream().map(ent->ent.getOperaciones()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
        List<Presupuesto> presupuestos = new ArrayList<>();
       List<Criterio> criterios = egresos.stream().map(e->e.getCriterioDeCategorizacion()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
        List<CategoriaCriterio> categorias=criterios.stream().map(c->c.getCategoriaCriterios()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
        Set<CategoriaCriterio> categoriasSet= new HashSet<>();
        categoriasSet.addAll(categorias);
        categorias.clear();
        categorias.addAll(categoriasSet);

        datos.put("egresosPactados",egresos.stream().filter(e->e.getPresupuestoPactado()!=null).collect(Collectors.toList()));
        datos.put("egresosNoPactados",egresos.stream().filter(e->e.getPresupuestoPactado()==null).collect(Collectors.toList()));
        datos.put("presupuesto",presupuestos);
        datos.put("categorias",categorias);

        ModelAndView vista = new ModelAndView(datos, "asociar_egresos_y_presupuestos.html");

        return vista;
    }

    public static Object asociarEgresosYPresupuestos(Request request, Response response) {

        String entidad = request.queryParams("entidad");
        String fecha = request.queryParams("fecha");
        String metodoPago = request.queryParams("metodoPago");
        String egreso = request.queryParams("egreso");
        String presupuesto = request.queryParams("presupuesto");

        //Integer egresoId = Integer.valueOf(egreso);
        //Integer presupuestoId = Integer.valueOf(presupuesto);
        //Le pasamos los ids a la base así me devuelve los dos objetitos
        //egresoPosta.setPresupuestoPactado(presupuestoPosta);

        response.redirect("asociar_egresos_y_presupuestos");

        return null;
    }

    public static Object asociarIngresosYEgresos(Request request, Response response) throws NoPuedoAsignarMasDineroDelQueTengoException {

        String entidad = request.queryParams("entidad"); //no me interesa
        String fecha = request.queryParams("fecha");
        String metodoPago = request.queryParams("metodoPago");//no me interesa
        String egreso = request.queryParams("egreso");
        String ingreso = request.queryParams("ingreso");

        Integer egresoId = Integer.valueOf(egreso);
        Integer ingresoId = Integer.valueOf(ingreso);

        DAO DAOEgreso = new DAOBBDD<Egreso>(Egreso.class);
        Repositorio repoEgreso = new Repositorio<Egreso>(DAOEgreso);

        DAO DAOIngreso = new DAOBBDD<Ingreso>(Ingreso.class);
        Repositorio repoIngreso = new Repositorio<Ingreso>(DAOIngreso);

        List<Egreso> egresos = repoEgreso.getTodosLosElementos();
        int i = egresos.indexOf(egresoId);
        Egreso objEgreso = egresos.get(i);

        List<Ingreso> ingresos = repoIngreso.getTodosLosElementos();
        int j = egresos.indexOf(ingresoId);
        Ingreso objIngreso = ingresos.get(j);
        Ingreso objIngresoModificado = ingresos.get(j);

        objIngresoModificado.agregarEgreso(objEgreso);

        repoIngreso.modificar(objIngreso,objIngresoModificado);

        response.redirect("asociar_ingresos_y_egresos");

        return null;
    }

    /*
    public static void persistirAsociacionPyE(){

        DAO DAOEgreso = new DAOBBDD<Criterio>(Criterio.class); //dao generico de BBDD
        Repositorio repoCriterio = new Repositorio<Criterio>(DAOCriterio); //repositorio que tambien usa generics

        List<Criterio> criterios = repoCriterio.getTodosLosElementos();

        List<Criterio> criteriosPosibles=criterios.stream().filter(c-> c.getNombreCriterio().equals(criterioAsociado)).collect(Collectors.toList());

        if(criteriosPosibles.isEmpty()){
            return;
        }

        Criterio criterio = criteriosPosibles.get(0);
        Criterio criterioModificado = criteriosPosibles.get(0);

        criterioModificado.agregarCategoria(categoriaCriterio);

        //Verificar que esto este bien
        repoCriterio.modificar(criterio, criterioModificado);
    }
    */
}

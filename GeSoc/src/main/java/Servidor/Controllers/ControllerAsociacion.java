package Servidor.Controllers;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Entidad.Entidad;
import Dominio.Ingreso.Excepciones.NoPuedoAsignarMasDineroDelQueTengoException;
import Dominio.Ingreso.Ingreso;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerAsociacion {

    public static ModelAndView visualizarPantallaIngresosYEgresos(Request request, Response response){

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        Map<String,Object> datos = new HashMap<>();
        if(miUsuario==null){
            response.redirect("/");
            return null;
        }
        List<Egreso> egresos = miUsuario.getEgresosAREvisar();
        List<Ingreso> ingresos = egresos.stream().map(e->e.getEntidad().getIngresos()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());

        datos.put("egresos",egresos);
        datos.put("ingresos",ingresos);

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
        List<Egreso> egresos = miUsuario.getEgresosAREvisar();//no le pongo todos porque sino es una guazada

       List<Criterio> criterios = egresos.stream().map(e->e.getCriterioDeCategorizacion()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
        List<CategoriaCriterio> categorias=criterios.stream().map(c->c.getCategoriaCriterios()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
        Set<CategoriaCriterio> categoriasSet= new HashSet<>();
        categoriasSet.addAll(categorias);
        categorias.clear();
        categorias.addAll(categoriasSet);

        List<Presupuesto> presupuestos = egresos.stream().filter(e->e.getPresupuestoPactado()==null).collect(Collectors.toList()).stream().map(e->e.getPresupuestosAConsiderar()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
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

package Servidor.Controllers;

import API.Vinculacion.Condicion;
import API.Vinculacion.ControllerVinculacion;
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

import java.time.LocalDate;
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

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        String egreso = request.queryParams("egreso");
        String presupuesto = request.queryParams("presupuesto");

        int statusCode=0;
        Integer egresoId = Integer.valueOf(egreso);
        Integer presupuestoId = Integer.valueOf(presupuesto);
        List<Egreso> egresosPosibles=miUsuario.getEgresosAREvisar().stream().filter(e->e.getEgreso()==egresoId.intValue()).collect(Collectors.toList());
        List<Presupuesto> presupuestosPosibles=egresosPosibles.stream().filter(e->e.getPresupuestoPactado()==null).collect(Collectors.toList()).stream().map(e->e.getPresupuestosAConsiderar()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());

        if(!egresosPosibles.isEmpty()&& !presupuestosPosibles.isEmpty()) {
            Egreso egresoPosta = egresosPosibles.get(0);
            Presupuesto presupuestoPosta=presupuestosPosibles.get(0);
            egresoPosta.setPresupuestoPactado(presupuestoPosta);

            Repositorio repoEgresos= new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
            repoEgresos.modificar(null,egresoPosta);
            statusCode=1;
        }
        response.redirect("asociar_egresos_y_presupuestos?Exito="+statusCode);
        return null;
    }

    public static Object asociarIngresosYEgresos(Request request, Response response) throws NoPuedoAsignarMasDineroDelQueTengoException {


        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        String estrategia = request.queryParams("estrategia");
        String fecha = request.queryParams("fecha");
        String egreso = request.queryParams("egreso");
        String ingreso = request.queryParams("ingreso");


        int statusCode=0;
        if(estrategia.equals("Manual")){
            //no delego nada y lo hago aca

            Integer egresoId = Integer.valueOf(egreso);
            Integer ingresoId = Integer.valueOf(ingreso);


            DAO DAOIngreso = new DAOBBDD<Ingreso>(Ingreso.class);
            Repositorio repoIngreso = new Repositorio<Ingreso>(DAOIngreso);

            int idEgreso= egresoId.intValue();
            List<Egreso> egresos =miUsuario.getEgresosAREvisar();
            List<Egreso> egresosPosibles=egresos.stream().filter(e->e.getEgreso()==idEgreso).collect(Collectors.toList());

            int idIngreso= ingresoId.intValue();
            List<Ingreso> ingresos =miUsuario.getEgresosAREvisar().stream().map(e->e.getEntidad().getIngresos()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
            List<Ingreso> ingresosPosibles=ingresos.stream().filter(e->e.getIngreso()==idIngreso).collect(Collectors.toList());


            if(!ingresosPosibles.isEmpty()&& !egresosPosibles.isEmpty()){

             Egreso egresoPosta=egresosPosibles.get(0);
             Ingreso ingresoPosta=ingresosPosibles.get(0);
             egresoPosta.setIngreso(ingresoPosta);
             ingresoPosta.agregarEgreso(egresoPosta);

             Repositorio repoEgresos= new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
             Repositorio repoIngresos= new Repositorio(new DAOBBDD<Ingreso>(Ingreso.class));
             repoEgresos.modificar(null,egresoPosta);
             repoIngreso.modificar(null,ingresoPosta);
            }
        }else if(estrategia.equals("Automática")){
            //se lo mando a la API
            //todo
            List<Ingreso> ingresos = miUsuario.getEntidades().stream().map(x -> x.getIngresos()).flatMap(List::stream).collect(Collectors.toList());
            List<Egreso> egresos = miUsuario.getEgresosAREvisar();
            List<String> criterios =new ArrayList<>(); //Son los checkboxes
            List<Condicion> condiciones = new ArrayList<>();

            if(request.queryParams("OVPE")!=null){
                criterios.add("OrdenValorPrimeroEgreso");
            }

            if(request.queryParams("OVPI")!=null){
                criterios.add("OrdenValorPrimeroIngreso");
            }

            if(request.queryParams("OrdenFecha")!=null){
                criterios.add("OrdenFechaPrimerEgreso");
            }

            System.out.println(fecha);

            LocalDate fechaHasta = LocalDate.parse(fecha);
            LocalDate fechaDesde = LocalDate.now();

            System.out.println(fechaHasta);
            System.out.println(fechaDesde);

            Integer diferenciaDia = fechaDesde.getDayOfYear() - fechaHasta.getDayOfYear();

            System.out.println(diferenciaDia);

            List<Object> parametros = new ArrayList<>();
            parametros.add(diferenciaDia);
            Condicion condicion = new Condicion("PeriodoAceptacion",parametros);
            condiciones.add(condicion);

            ControllerVinculacion.obtenerInstacia().vincular(egresos, ingresos, criterios,condiciones);
        }

        response.redirect("asociar_ingresos_y_egresos?Exito="+statusCode);

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

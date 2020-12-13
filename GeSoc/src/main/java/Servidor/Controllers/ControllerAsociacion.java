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
import Persistencia.QueriesUtiles;
import Persistencia.Repos.Repositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ControllerAsociacion {

    public static ModelAndView visualizarPantallaIngresosYEgresos(Request request, Response response){

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        Map<String,Object> datos = new HashMap<>();
        if(miUsuario==null){
            response.redirect("/");
            return null;
        }

        List<Egreso> egresos = QueriesUtiles.obtenerEgresosDe(miUsuario.getNickName());

        List<Entidad> entidades= miUsuario.getEntidades();
        Set<Entidad> entidadSet= new HashSet<>();
        entidadSet.addAll(entidades);
        entidades.clear();
        entidades.addAll(entidadSet);
        //List<Ingreso> ingresos = egresos.stream().map(e->e.getEntidad().getIngresos()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
        final List<Ingreso>[] ingresosQueManejo = new List[]{entidades.stream().map(e -> e.getIngresos()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList())};

        datos.put("egresos",egresos);
        datos.put("ingresos",ingresosQueManejo[0]);

        ModelAndView vista = new ModelAndView(datos, "asociar_ingresos_y_egresos.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaEgresosYPresupuestos(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);



        List<Egreso> egresos = QueriesUtiles.obtenerEgresosDe(miUsuario.getNickName());

        List<Criterio> criterios = egresos.stream().map(e->e.getCriterioDeCategorizacion()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
        List<CategoriaCriterio> categorias=criterios.stream().map(c->c.getCategoriaCriterios()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());
        Set<CategoriaCriterio> categoriasSet= new HashSet<>();
        categoriasSet.addAll(categorias);
        categorias.clear();
        categorias.addAll(categoriasSet);

        List<Presupuesto> presupuestos = egresos.stream().map(e-> QueriesUtiles.obtenerPresupuestosDe(e)).flatMap(List::stream).collect(Collectors.toList());

        datos.put("egresosPactados",QueriesUtiles.obtenerEgresosPactados(miUsuario.getNickName()));
        datos.put("egresosNoPactados",QueriesUtiles.obtenerEgresosNoPactados(miUsuario.getNickName()));
        datos.put("presupuesto",presupuestos);
        datos.put("categorias",categorias);

        ModelAndView vista = new ModelAndView(datos, "asociar_egresos_y_presupuestos.html");

        return vista;
    }

    public static Object asociarEgresosYPresupuestos(Request request, Response response) {

        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        String egreso = request.queryParams("egreso");
        String presupuesto = request.queryParams("presupuesto");

        AtomicInteger statusCode= new AtomicInteger(0);
        Integer egresoId = Integer.valueOf(egreso);
        Integer presupuestoId = Integer.valueOf(presupuesto);
        List<Egreso> egresosPosibles=QueriesUtiles.obtenerEgresosDe(miUsuario.getNickName()).stream().filter(e->e.getEgreso()==egresoId.intValue()).collect(Collectors.toList());
        List<Presupuesto> presupuestosPosibles=egresosPosibles.stream().filter(e->e.getPresupuestoPactado()==null).map(e->QueriesUtiles.obtenerPresupuestosDe(e)).flatMap(List::stream).collect(Collectors.toList());

        egresosPosibles.stream().findFirst().ifPresent(egresoPosible->{
           presupuestosPosibles.stream().filter(pres->pres.getPresupuesto()==Integer.valueOf(presupuestoId)).findFirst().ifPresent(presu->{
                egresoPosible.setPresupuestoPactado(presu);
                Repositorio repoEgreso= new Repositorio<Egreso>(new DAOBBDD<Egreso>(Egreso.class));
                repoEgreso.modificar(null,egresoPosible);
                statusCode.set(1);
            });
        });
        response.redirect("asociar_egresos_y_presupuestos?Exito="+statusCode.get());
        return null;
    }

    public static Object asociarIngresosYEgresos(Request request, Response response) {


        Usuario miUsuario= ControllerSesion.obtenerUsuariodeSesion(request);
        String estrategia = request.queryParams("estrategia");

        String egreso = request.queryParams("egreso");
        String ingreso = request.queryParams("ingreso");


        int statusCode=0;
        if(estrategia.equals("Manual")){
            Integer egresoId = Integer.valueOf(egreso);
            Integer ingresoId = Integer.valueOf(ingreso);
            //no delego nada y lo hago aca
            try {
                realizarAsociacionManual(egresoId, ingresoId,miUsuario);
                statusCode=1;
            } catch (NoPuedoAsignarMasDineroDelQueTengoException e) {
                e.printStackTrace();
            }
        }else if(estrategia.equals("Automática")){
            //se lo mando a la API
            //todo
            realizarAsociacionAutomatica(request,response,miUsuario);
            statusCode=1;
        }

        response.redirect("asociar_ingresos_y_egresos?Exito="+statusCode);

        return null;
    }

    private static void realizarAsociacionAutomatica(Request request, Response response, Usuario miUsuario) {

        List<Ingreso> ingresos = miUsuario.getEntidades().stream().map(x -> x.getIngresos()).flatMap(List::stream).collect(Collectors.toList());
        //Esto deberia traerme los egresos que no estan vinculados
        List<Egreso> egresos = QueriesUtiles.obtenerEgresosDe(miUsuario.getNickName()).stream().filter(e -> e.getIngreso() == null).collect(Collectors.toList());
        List<String> criterios =new ArrayList<>(); //Son los checkboxes
        List<Condicion> condiciones = new ArrayList<>();
        String fecha = request.queryParams("fecha");

        if(request.queryParams("OVPE")!=null){
            criterios.add("OrdenValorPrimeroEgreso");
        }

        if(request.queryParams("OVPI")!=null){
            criterios.add("OrdenValorPrimeroIngreso");
        }

        if(request.queryParams("OrdenFecha")!=null){
            criterios.add("OrdenFechaPrimerEgreso");
        }


        LocalDate fechaHasta = LocalDate.parse(fecha);
        LocalDate fechaDesde = LocalDate.now();

        Integer diferenciaDia = fechaDesde.getDayOfYear() - fechaHasta.getDayOfYear();

        List<Object> parametros = new ArrayList<>();
        parametros.add(diferenciaDia);
        Condicion condicion = new Condicion("PeriodoAceptacion",parametros);
        condiciones.add(condicion);

        ControllerVinculacion.obtenerInstacia().vincular(egresos, ingresos, criterios,condiciones);
    }

    private static void realizarAsociacionManual(Integer egresoId, Integer ingresoId,Usuario miUsuario) throws NoPuedoAsignarMasDineroDelQueTengoException {

        DAO DAOIngreso = new DAOBBDD<Ingreso>(Ingreso.class);
        Repositorio repoIngreso = new Repositorio<Ingreso>(DAOIngreso);

        int idEgreso = egresoId.intValue();
        List<Egreso> egresos =QueriesUtiles.obtenerEgresosDe(miUsuario.getNickName());
        List<Egreso> egresosPosibles = egresos.stream().filter(e -> e.getEgreso() == idEgreso).collect(Collectors.toList());

        int idIngreso = ingresoId.intValue();
        List<Ingreso> ingresos = miUsuario.getEntidades().stream().map(ent-> QueriesUtiles.obtenerTodosLosIngresosDe(ent)).flatMap(List::stream).collect(Collectors.toList());
        List<Ingreso> ingresosPosibles = ingresos.stream().filter(e -> e.getIngreso() == idIngreso).collect(Collectors.toList());


        if (!ingresosPosibles.isEmpty() && !egresosPosibles.isEmpty()) {

            Egreso egresoPosta = egresosPosibles.get(0);
            Ingreso ingresoPosta = ingresosPosibles.get(0);
            egresoPosta.setIngreso(ingresoPosta);
            ingresoPosta.agregarEgreso(egresoPosta);

            //Actualizo el importe de mi ingreso
            ingresoPosta.disminuirValor(egresoPosta.getValor().getImporte());

            Repositorio repoEgresos = new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
            Repositorio repoIngresos = new Repositorio(new DAOBBDD<Ingreso>(Ingreso.class));
            repoEgresos.modificar(null, egresoPosta);
            repoIngreso.modificar(null, ingresoPosta);

        }

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

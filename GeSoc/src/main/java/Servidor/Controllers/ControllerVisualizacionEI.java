package Servidor.Controllers;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.Egreso;
import Dominio.Entidad.Entidad;
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

public class ControllerVisualizacionEI {

    public static ModelAndView visualizarPantalla(Request request, Response response){
        ControllerSesion.obtenerUsuariodeSesion(request);
        Map<String,Object> datos = new HashMap<>();

        DAO daoEntidad = new DAOBBDD<Entidad>(Entidad.class);
        Repositorio repoEntidades = new Repositorio<Entidad>(daoEntidad);

        DAO daoEgreso = new DAOBBDD<Egreso>(Egreso.class);
        Repositorio repoEgresos = new Repositorio<Egreso>(daoEgreso);

        DAO daoIngreso = new DAOBBDD<Ingreso>(Ingreso.class);
        Repositorio repoIngresos = new Repositorio<Ingreso>(daoIngreso);

        DAO daoCriterio = new DAOBBDD<Criterio>(Criterio.class);
        Repositorio repoCriterios = new Repositorio<Criterio>(daoCriterio);

        DAO daoCategoria = new DAOBBDD<CategoriaCriterio>(CategoriaCriterio.class);
        Repositorio repoCategorias = new Repositorio<Criterio>(daoCategoria);

        Usuario usuarioActual= ControllerSesion.obtenerUsuariodeSesion(request);
        //Estas cinco listas las tenemos que traer de la BD
        List<Entidad> entidades = repoEntidades.getTodosLosElementos();
        List<Ingreso> ingreso = repoIngresos.getTodosLosElementos();
        entidades.forEach(ent->ingreso.addAll(ent.getIngresos()));
        List<Egreso> egreso = repoEgresos.getTodosLosElementos();

        List<CategoriaCriterio> categorias = repoCategorias.getTodosLosElementos();
        //List<CategoriaCriterio> categorias = egreso.stream().map(eg->eg.getCategorias()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());

        List<Criterio> criterios = repoCriterios.getTodosLosElementos();
        //List<Criterio> criterios = egreso.stream().map(eg->eg.getCriterioDeCategorizacion()).collect(Collectors.toList()).stream().flatMap(List::stream).collect(Collectors.toList());

        //remuevo repetidos
        Set<Entidad> setEntidades = new HashSet<Entidad>(entidades);
        entidades.clear();
        entidades.addAll(setEntidades);
        Set<Egreso> setEgreso = new HashSet<Egreso>(egreso);
        egreso.clear();
        egreso.addAll(setEgreso);
        Set<Ingreso> setIngreso = new HashSet<Ingreso>(ingreso);
        ingreso.clear();
        ingreso.addAll(setIngreso);
        Set<CategoriaCriterio> setCategorias = new HashSet<CategoriaCriterio>(categorias);
        categorias.clear();
        categorias.addAll(setCategorias);
        Set<Criterio> setCriterios = new HashSet<Criterio>(criterios);
        criterios.clear();
        criterios.addAll(setCriterios);




        //Logica de filtrado
        List<Entidad> entidadesFiltradas = new ArrayList<>();
        List<Criterio> criteriosFiltradas = new ArrayList<>();
        List<CategoriaCriterio> categoriasFiltradas = new ArrayList<>();
        List<Ingreso> ingresoFiltradas = new ArrayList<>();
        List<Egreso> egresoFiltradas = new ArrayList<>();

        entidadesFiltradas.addAll(entidades);
        criteriosFiltradas.addAll(criterios);
        categoriasFiltradas.addAll(categorias);
        ingresoFiltradas.addAll(ingreso);
        egresoFiltradas.addAll(egreso);

        String idEntidad = request.queryParams("entidad");
        String idCriterio = request.queryParams("criterio");
        String idCategoria = request.queryParams("categoria");


        if(idEntidad != null){
            //Filtramos entidades
            egresoFiltradas = egresoFiltradas.stream().filter(unEgreso->String.valueOf(unEgreso.getEntidad().getEntidad()).equals(idEntidad)).collect(Collectors.toList());
            ingresoFiltradas = ingresoFiltradas.stream().filter(unIngreso->String.valueOf(unIngreso.getEntidad().getEntidad()).equals(idEntidad)).collect(Collectors.toList());
        }

        if(idCriterio != null){
            //Filtramos criterios
            egresoFiltradas = egresoFiltradas.stream().filter(unEgreso->criterios.stream().anyMatch(x->unEgreso.getCriterioDeCategorizacion().contains(x))).collect(Collectors.toList());
            ingresoFiltradas = ingresoFiltradas.stream().filter(unIngreso->criterios.stream().anyMatch(x->x.getCategoriaCriterios().stream().anyMatch(unCriterio-> unIngreso.getCategoriasAsociadas().contains(unCriterio)))).collect(Collectors.toList());
        }

        if(idCategoria != null){
            //Filtramos categorias
            egresoFiltradas = egresoFiltradas.stream().filter(unEgreso->unEgreso.getCategorias().stream().anyMatch(unaCategoria -> String.valueOf(unaCategoria.getCategoria()).equals(idCategoria))).collect(Collectors.toList());
            ingresoFiltradas = ingresoFiltradas.stream().filter(unIngreso->unIngreso.getCategoriasAsociadas().stream().anyMatch(unaCategoria -> String.valueOf(unaCategoria.getCategoria()).equals(idCategoria))).collect(Collectors.toList());
        }

        //Hacemos los PUTs
        datos.put("entidades",entidadesFiltradas);
        datos.put("criterios",criteriosFiltradas);
        datos.put("categorias",categoriasFiltradas);
        datos.put("ingreso",ingresoFiltradas);
        datos.put("egreso",egresoFiltradas);

        ModelAndView vista = new ModelAndView(datos, "ver_ingresos_y_egresos.html");

        return vista;
    }

}

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
import Dominio.Ingreso.Ingreso;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerVisualizacionEI {

    public static ModelAndView visualizarPantalla(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();

        //Estas cinco listas las tenemos que traer de la BD
        List<Entidad> entidades = new ArrayList<>();
        List<Criterio> criterios = new ArrayList<>();
        List<CategoriaCriterio> categorias = new ArrayList<>();
        List<Ingreso> ingreso = new ArrayList<>();
        List<Egreso> egreso = new ArrayList<>();

        //Tests
        Entidad entidadPrueba1 = new EntidadJuridica("entidadPrueba1", "JorgeCampeon",new OrganizacionSocial());
        entidadPrueba1.setEntidad(57);
        entidades.add(entidadPrueba1);

        Entidad entidadPrueba2 = new EntidadJuridica("entidadPrueba2", "JuanCampeon",new OrganizacionSocial());
        entidadPrueba2.setEntidad(37);
        entidades.add(entidadPrueba2);

        Criterio criterioPrueba = new Criterio(new ArrayList<>(),"criterioPrueba", "TinchoCampeon");
        criterioPrueba.setCriterio(13);
        criterios.add(criterioPrueba);

        CategoriaCriterio categoriaPrueba = new CategoriaCriterio("descripCat","catgoriaPrueba");
        categoriaPrueba.setCategoria(9);
        categorias.add(categoriaPrueba);

        Ingreso ingresoPrueba = new Ingreso("$",230,LocalDate.of(2020,10,14),"descrip",new ArrayList<>());
        Ingreso ingresoPrueba2 = new Ingreso("$",10,LocalDate.of(2020,10,14),"descrip2",new ArrayList<>());
        Ingreso ingresoPrueba3 = new Ingreso("$",3,LocalDate.of(2020,10,14),"descrip3",new ArrayList<>());

        Egreso egresoPrueba = new Egreso(LocalDate.parse("2014-02-14"), "Uruguay", 8888, new ArrayList<>(), new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO, "TD"), new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.REMITO, "datojajaj"), new CriterioSeleccionProveedor() {
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

        Egreso egresoPrueba2 = new Egreso(LocalDate.parse("2013-02-14"), "Paraguay", 2222, new ArrayList<>(), new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO, "TD"), new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.REMITO, "datos.jajaj"), new CriterioSeleccionProveedor() {
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

        //Seteamos las entidades
        ingresoPrueba.setEntidad(entidadPrueba1);
        ingresoPrueba2.setEntidad(entidadPrueba2);
        ingresoPrueba3.setEntidad(entidadPrueba1);

        ingresoPrueba.agregarCategoria(categoriaPrueba);

        egresoPrueba.setEntidad(entidadPrueba1);
        egresoPrueba2.setEntidad(entidadPrueba2);

        egresoPrueba2.getCriterioDeCategorizacion().add(criterioPrueba);

        //Agregamos los objetitos a las listas
        egreso.add(egresoPrueba);
        egreso.add(egresoPrueba2);

        ingreso.add(ingresoPrueba);
        ingreso.add(ingresoPrueba2);
        ingreso.add(ingresoPrueba3);

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
            egresoFiltradas = egreso.stream().filter(unEgreso->String.valueOf(unEgreso.getEntidad().getEntidad()).equals(idEntidad)).collect(Collectors.toList());
            ingresoFiltradas = ingreso.stream().filter(unIngreso->String.valueOf(unIngreso.getEntidad().getEntidad()).equals(idEntidad)).collect(Collectors.toList());
        }

        if(idCriterio != null){
            //Filtramos criterios
            egresoFiltradas = egreso.stream().filter(unEgreso->criterios.stream().anyMatch(x->unEgreso.getCriterioDeCategorizacion().contains(x))).collect(Collectors.toList());
            ingresoFiltradas = ingreso.stream().filter(unIngreso->criterios.stream().anyMatch(x->x.getCategoriaCriterios().stream().anyMatch(unCriterio-> unIngreso.getCategoriasAsociadas().contains(unCriterio)))).collect(Collectors.toList());
        }

        if(idCategoria != null){
            //Filtramos categorias
            egresoFiltradas = egreso.stream().filter(unEgreso->unEgreso.getCategorias().stream().anyMatch(unaCategoria -> String.valueOf(unaCategoria.getCategoria()).equals(idCategoria))).collect(Collectors.toList());
            ingresoFiltradas = ingreso.stream().filter(unIngreso->unIngreso.getCategoriasAsociadas().stream().anyMatch(unaCategoria -> String.valueOf(unaCategoria.getCategoria()).equals(idCategoria))).collect(Collectors.toList());
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

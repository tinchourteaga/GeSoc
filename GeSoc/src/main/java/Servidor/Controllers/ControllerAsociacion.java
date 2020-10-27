package Servidor.Controllers;

import Dominio.Egreso.Core.*;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Dominio.Entidad.Entidad;
import Dominio.Entidad.EntidadJuridica;
import Dominio.Entidad.OrganizacionSocial;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerAsociacion {

    public static ModelAndView visualizarPantallaIngresosYEgresos(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();
        ModelAndView vista = new ModelAndView(datos, "asociar_ingresos_y_egresos.html");

        return vista;
    }

    public static ModelAndView visualizarPantallaEgresosYPresupuestos(Request request, Response response){

        Map<String,Object> datos = new HashMap<>();

        //Estas cinco listas las tenemos que traer de la BD
        List<Entidad> entidades = new ArrayList<>();

        //Tests
        List<Egreso> egresos = new ArrayList<>();
        List<Presupuesto> presupuestos = new ArrayList<>();
        List<CategoriaCriterio> categorias = new ArrayList<>();

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

        Presupuesto presupuesto1 = new Presupuesto(new ArrayList<>(),233f,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.REMITO,"remito"),new Proveedor("","","",null));
        Presupuesto presupuesto2 = new Presupuesto(new ArrayList<>(),500f,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.REMITO,"remito"),new Proveedor("","","",null));

        presupuesto1.setPresupuesto(23);
        presupuesto2.setPresupuesto(3);

        presupuesto1.setFecha(LocalDate.of(2020,7,27));
        presupuesto2.setFecha(LocalDate.of(2020,7,21));

        CategoriaCriterio categoriaPrueba = new CategoriaCriterio("descripCat","catgoriaPrueba");
        categoriaPrueba.setCategoria(9);
        categorias.add(categoriaPrueba);

        Entidad entidadPrueba1 = new EntidadJuridica("entidadPrueba1", "JorgeCampeon",new OrganizacionSocial());
        entidadPrueba1.setEntidad(57);

        Entidad entidadPrueba2 = new EntidadJuridica("entidadPrueba2", "JuanCampeon",new OrganizacionSocial());
        entidadPrueba2.setEntidad(37);

        entidades.add(entidadPrueba1);
        entidades.add(entidadPrueba2);

        presupuestos.add(presupuesto1);
        presupuestos.add(presupuesto2);

        egresoPrueba.setEntidad(entidadPrueba1);
        egresoPrueba2.setEntidad(entidadPrueba1);

        egresoPrueba.setPresupuestoPactado(presupuesto1);
        egresoPrueba2.setPresupuestoPactado(presupuesto2);

        egresos.add(egresoPrueba);
        egresos.add(egresoPrueba2);

        datos.put("egreso",egresos);
        datos.put("entidad",entidades);
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

        Integer egresoId = Integer.valueOf(egreso);
        Integer presupuestoId = Integer.valueOf(presupuesto);
        //Le pasamos los ids a la base así me devuelve los dos objetitos
        //egresoPosta.setPresupuestoPactado(presupuestoPosta);

        response.redirect("asociar_egresos_y_presupuestos");

        return null;
    }

    public static Object asociarIngresosYEgresos(Request request, Response response) {

        String entidad = request.queryParams("entidad");
        String fecha = request.queryParams("fecha");
        String metodoPago = request.queryParams("metodoPago");
        String egreso = request.queryParams("egreso");
        String ingreso = request.queryParams("ingreso");

        response.redirect("asociar_ingresos_y_egresos");

        return null;
    }
}

package Dominio;

import API.Vinculacion.Condicion;
import API.Vinculacion.ControllerVinculacion;
import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Egreso.Core.*;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Dominio.Ingreso.Ingreso;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {

        Ingreso unIngreso = new Ingreso("Argentina",557000, LocalDate.parse("2014-02-14"),"pruebita",new ArrayList<>());
        Ingreso otroIngreso = new Ingreso("Brasil",135000, LocalDate.parse("2020-02-07"),"pruebita2",new ArrayList<>());

        List<Ingreso> ingresos = new ArrayList<>();
        unIngreso.setIngreso(1);
        otroIngreso.setIngreso(2);
        ingresos.add(unIngreso);
        ingresos.add(otroIngreso);

        Egreso unEgreso = new Egreso(LocalDate.parse("2014-02-14"), "Uruguay", 8888, new ArrayList<>(), new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO, "TD"), new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.REMITO, "datojajaj"), new CriterioSeleccionProveedor() {
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

        Egreso otroEgreso = new Egreso(LocalDate.parse("2013-02-14"), "Paraguay", 2222, new ArrayList<>(), new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO, "TD"), new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.REMITO, "datos.jajaj"), new CriterioSeleccionProveedor() {
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

        List<Egreso> egresos = new ArrayList<>();
        unEgreso.setEgreso(1);
        unEgreso.setEgreso(2);
        egresos.add(unEgreso);
        egresos.add(otroEgreso);

        List<String> criterios =new ArrayList<>();
        criterios.add("OrdenValorPrimeroEgreso");
        criterios.add("OrdenValorPrimeroIngreso");

        List<Condicion> condiciones = new ArrayList<>();
        List<Object> parametros = new ArrayList<>();
        parametros.add(7);

        Condicion condicion = new Condicion("PeriodoAceptacion",parametros);

        condiciones.add(condicion);

        ControllerVinculacion.obtenerInstacia().vincular(egresos, ingresos, criterios,condiciones);

        //ControllerVinculacion.obtenerInstacia().vincular(new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>());

        System.out.println("Los egresos vinculados al ingreso "+unIngreso.getIngreso()+" es: "+unIngreso.getGastadoEn());

        System.out.println("Los egresos vinculados al ingreso "+otroIngreso.getIngreso()+" es: "+otroIngreso.getGastadoEn());
    }
}
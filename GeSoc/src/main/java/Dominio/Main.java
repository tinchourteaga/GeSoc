package Dominio;

import Dominio.Egreso.Core.*;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioMenorPrecio;
import Dominio.Egreso.Validador.EstrategiasRevision.EjecucionAutomatica;
import Dominio.Egreso.Validador.Validaciones.ValidacionPresupuestoMenor;
import Dominio.Egreso.Validador.Validaciones.ValidacionCantidadPresupuestos;
import Dominio.Egreso.Validador.Validaciones.ValidacionCompraPertenecePresupuesto;
import Dominio.Egreso.Validador.Validaciones.ValidacionCriterioProveedor;
import Dominio.Egreso.Validador.ValidadorDeOperacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main (String [ ] args) {

        ValidadorDeOperacion.setEstrategia(new EjecucionAutomatica(19,33));

        List<Presupuesto> presupuestos=new ArrayList<>();
        presupuestos.add(new Presupuesto(new ArrayList<>(),51000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),52000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),53000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        List<Proveedor> proveedores=new ArrayList();
        proveedores.add(new Proveedor("asdasd","aaaa bbbb","28483672816","aaaa",presupuestos));
        proveedores.add(new Proveedor("agdasd","aaaa b4bb","28493672816","bbbb",presupuestos));
        proveedores.add(new Proveedor("aydasd","aaaa bbtb","28473672816","cccc",presupuestos));

        Egreso unEgreso=new Egreso(new Date(),51000, new ArrayList<>(),new MetodoDePago(TipoDeMedioDePago.CHEQUE,"as"),proveedores,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioMenorPrecio());

        ValidadorDeOperacion.getValidaciones().clear();
        ValidadorDeOperacion.getValidaciones().addAll((new ArrayList(){{add(new ValidacionPresupuestoMenor(proveedores)); add(new ValidacionCompraPertenecePresupuesto(proveedores)); add(new ValidacionCantidadPresupuestos(proveedores)); add(new ValidacionCriterioProveedor());}}));
        //ValidadorDeOperacion.getRepositorio().getAllElementos().addAll((new ArrayList(){{add(new ValidacionPresupuestoMenor(proveedores)); add(new ValidacionCompraPertenecePresupuesto(proveedores)); add(new ValidacionCantidadPresupuestos(proveedores)); add(new ValidacionCriterioProveedor());}}));

        ValidadorDeOperacion.validarPorEstrategia(unEgreso);
        System.out.println(unEgreso.isEstaVerificada());


        System.out.println(unEgreso.isEstaVerificada());

    }
}

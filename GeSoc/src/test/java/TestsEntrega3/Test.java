package TestsEntrega3;

import BandejaMensajes.Mensaje;
import Egreso.Core.*;
import Egreso.Core.CriteriosProveedor.CriterioMenorPrecio;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Egreso.Validador.ValidadorDeOperacion;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    @org.junit.Test
    public void testValidadorNoPasaCriterio(){
        List<Presupuesto> presupuestos=new ArrayList<>();
        presupuestos.add(new Presupuesto(new ArrayList<>(),51000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),52000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),53000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),504000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),55000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),56000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        List<Proveedor> proveedores=new ArrayList();
        proveedores.add(new Proveedor("asdasd","aaaa bbbb","28483672816","aaaa",presupuestos.get(0)));
        proveedores.add(new Proveedor("agdasd","aaaa b4bb","28493672816","bbbb",presupuestos.get(5)));
        proveedores.add(new Proveedor("aydasd","aaaa bbtb","28473672816","cccc",presupuestos.get(1)));
        proveedores.add(new Proveedor("ahdasd","aaaa bcbb","28453672816","dddd",presupuestos.get(2)));
        proveedores.add(new Proveedor("ajdasd","aaaa bhbb","28443672816","eeee",presupuestos.get(3)));
        proveedores.add(new Proveedor("aldasd","aaaa bjbb","28433672816","ffff",presupuestos.get(4)));


        Egreso unEgreso=new Egreso(new Date(),100000, new ArrayList<>(),new MetodoDePago(),proveedores,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioMenorPrecio());
        Mensaje fallo=ValidadorDeOperacion.validarCustomSinBasicas(unEgreso,ValidadorDeOperacion.getValidaciones(), null);
        Assert.assertEquals(fallo.getMensajeResultado(), new NoCumpleValidacionDeCriterioException().toString());
    }
    @org.junit.Test
    public void testValidadorNoPasaOperacion(){
        List<Presupuesto> presupuestos=new ArrayList<>();
        presupuestos.add(new Presupuesto(new ArrayList<>(),51000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),52000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),53000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        List<Proveedor> proveedores=new ArrayList();
        proveedores.add(new Proveedor("asdasd","aaaa bbbb","28483672816","aaaa",presupuestos.get(0)));
        proveedores.add(new Proveedor("agdasd","aaaa b4bb","28493672816","bbbb",presupuestos.get(2)));
        proveedores.add(new Proveedor("aydasd","aaaa bbtb","28473672816","cccc",presupuestos.get(1)));


        Egreso unEgreso=new Egreso(new Date(),53000, new ArrayList<>(),new MetodoDePago(),proveedores,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioMenorPrecio());
        Mensaje fallo=ValidadorDeOperacion.validarCustomSinBasicas(unEgreso,ValidadorDeOperacion.getValidaciones(), null);
        Assert.assertEquals(fallo.getMensajeResultado(), new NoCumpleValidacionException().toString());
        //no entiendo porque no da este tests

    }
}

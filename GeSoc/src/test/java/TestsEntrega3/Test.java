package TestsEntrega3;

import Dominio.BandejaMensajes.Mensaje;
import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Egreso.Core.*;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Categoria;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Jerarquia;
import Dominio.Egreso.Validador.Validaciones.*;
import Dominio.Rol.Acciones.AgregarJerarquia;
import Dominio.Rol.Exepciones.NoTengoPermisosException;
import Dominio.Rol.Rol;
import Dominio.Rol.RolAdministrador;
import Dominio.Usuario.Usuario;
import TestsEntrega3.CriterioDummy.CriterioFalla;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioMenorPrecio;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Dominio.Egreso.Validador.ValidadorDeOperacion;
import org.junit.Assert;

import java.io.IOException;
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
        proveedores.add(new Proveedor("agdasd","aaaa b4bb","28493672816","bbbb",new ArrayList(){{add(presupuestos.get(5));}}));
        proveedores.add(new Proveedor("aydasd","aaaa bbtb","28473672816","cccc",new ArrayList(){{add(presupuestos.get(1));}}));
        proveedores.add(new Proveedor("ahdasd","aaaa bcbb","28453672816","dddd",new ArrayList(){{add(presupuestos.get(2));}}));
        proveedores.add(new Proveedor("asdasd","aaaa bbbb","28483672816","aaaa",new ArrayList(){{add(presupuestos.get(0));}}));
        proveedores.add(new Proveedor("ajdasd","aaaa bhbb","28443672816","eeee",new ArrayList(){{add(presupuestos.get(3));}}));
        proveedores.add(new Proveedor("aldasd","aaaa bjbb","28433672816","ffff",new ArrayList(){{add(presupuestos.get(4));}}));

        ValidacionPresupuestoMenor validacion1 = new ValidacionPresupuestoMenor(proveedores);
        ValidacionCompraPertenecePresupuesto validacion2 = new ValidacionCompraPertenecePresupuesto(proveedores);
        ValidacionCantidadPresupuestos validacion3 = new ValidacionCantidadPresupuestos(proveedores);
        ValidacionCriterioProveedor validacion4 = new ValidacionCriterioProveedor();

        Egreso unEgreso=new Egreso(new Date(),100000, new ArrayList<>(),new MetodoDePago(TipoDeMedioDePago.CHEQUE,"as"),proveedores,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioFalla());
        Mensaje fallo=ValidadorDeOperacion.validarCustomSinBasicas(unEgreso,(new ArrayList(){{add(validacion1); add(validacion2); add(validacion3); add(validacion4);}}));
        Assert.assertEquals( new NoCumpleValidacionDeCriterioException().toString(),fallo.getMensajeResultado());
    }
    @org.junit.Test
    public void testValidadorNoPasaOperacion() {
        List<Presupuesto> presupuestos = new ArrayList<>();
        presupuestos.add(new Presupuesto(new ArrayList<>(), 51000, new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO, "no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(), 52000, new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO, "no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(), 53000, new ArrayList<>(), new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO, "no hay doc")));
        List<Proveedor> proveedores = new ArrayList();
        proveedores.add(new Proveedor("asdasd", "aaaa bbbb", "28483672816", "aaaa", new ArrayList(){{add(presupuestos.get(0));}}));
        proveedores.add(new Proveedor("agdasd", "aaaa b4bb", "28493672816", "bbbb", new ArrayList(){{add(presupuestos.get(2));}}));
        proveedores.add(new Proveedor("aydasd", "aaaa bbtb", "28473672816", "cccc", new ArrayList(){{add(presupuestos.get(1));}}));

        ValidacionPresupuestoMenor validacion1 = new ValidacionPresupuestoMenor(proveedores);
        ValidacionCompraPertenecePresupuesto validacion2 = new ValidacionCompraPertenecePresupuesto(proveedores);
        ValidacionCantidadPresupuestos validacion3 = new ValidacionCantidadPresupuestos(proveedores);
        ValidacionCriterioProveedor validacion4 = new ValidacionCriterioProveedor();

        Egreso unEgreso=new Egreso(new Date(),51000, new ArrayList<>(),new MetodoDePago(TipoDeMedioDePago.CHEQUE,"as"),proveedores,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioMenorPrecio());
        Mensaje fallo=ValidadorDeOperacion.validarCustomSinBasicas(unEgreso,(new ArrayList(){{add(validacion1); add(validacion2); add(validacion3); add(validacion4);}}));
        Assert.assertEquals(new NoCumpleValidacionException().toString(),fallo.getMensajeResultado());
    }

    @org.junit.Test
    public void testJerarquias() throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun, NoTengoPermisosException, NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {
        RolAdministrador roladmin=new RolAdministrador();
        List<Categoria> categorias=new ArrayList<>();
        categorias.add(new Categoria("descripcion1","nombre1"));
        categorias.add(new Categoria("descripcion2","nombre2"));
        categorias.add(new Categoria("descripcion3","nombre3"));
        categorias.add(new Categoria("descripcion4","nombre4"));
        Criterio criterioDummy=new Criterio(categorias,"dummy","dummy");
        Jerarquia hoja=new Jerarquia(criterioDummy,new ArrayList<>());
        List<Jerarquia>hojas=new ArrayList<>();
        hojas.add(hoja);
        Jerarquia jerarquiaNueva= new Jerarquia(criterioDummy,hojas);
        AgregarJerarquia agregarJerarquia=new AgregarJerarquia(criterioDummy,jerarquiaNueva);
        roladmin.getAcciones().add(agregarJerarquia);
        List<Rol>roles=new ArrayList<>();
        roles.add(roladmin);
        Usuario unUsuario=new Usuario(roles,"pepito","SiestaContr4senia no funca me m@deo");
        List<Presupuesto> presupuestos=new ArrayList<>();
        presupuestos.add(new Presupuesto(new ArrayList<>(),51000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),52000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),53000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),54000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),55000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));

        List<Proveedor> proveedores=new ArrayList<>();
        proveedores.add(new Proveedor("asdasd","aaaa bbbb","28483672816","aaaa",new ArrayList(){{add(presupuestos.get(0));}}));
        proveedores.add(new Proveedor("agdasd","aaaa b4bb","28493672816","bbbb",new ArrayList(){{add(presupuestos.get(2));}}));
        proveedores.add(new Proveedor("aydasd","aaaa bbtb","28473672816","cccc",new ArrayList(){{add(presupuestos.get(1));}}));
        proveedores.add(new Proveedor("aydasd","aa6a bbtb","28573672816","ccfc",new ArrayList(){{add(presupuestos.get(3));}}));
        proveedores.add(new Proveedor("aydasd","aata bbtb","28773672816","ccgc",new ArrayList(){{add(presupuestos.get(4));}}));

        Egreso unEgreso=new Egreso(new Date(),53000, new ArrayList<>(),new MetodoDePago(TipoDeMedioDePago.CHEQUE,"as"),proveedores,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioMenorPrecio());
        roladmin.realizarAccion(agregarJerarquia);
        int cantidad_criterios=agregarJerarquia.getJerarquiaAsociada().getHijos().size()+1+agregarJerarquia.getJerarquiaAsociada().getHijos().get(0).getHijos().size();
        Assert.assertEquals(cantidad_criterios,3);
    }

    @org.junit.Test
    public void testValidadorPasaOperacion(){
        List<Presupuesto> presupuestos=new ArrayList<>();
        presupuestos.add(new Presupuesto(new ArrayList<>(),51000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),52000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),53000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),54000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),55000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));

        List<Proveedor> proveedores=new ArrayList();
        proveedores.add(new Proveedor("asdasd","aaaa bbbb","28483672816","aaaa",new ArrayList(){{add(presupuestos.get(0));}}));
        proveedores.add(new Proveedor("agdasd","aaaa b4bb","28493672816","bbbb",new ArrayList(){{add(presupuestos.get(2));}}));
        proveedores.add(new Proveedor("aydasd","aaaa bbtb","28473672816","cccc",new ArrayList(){{add(presupuestos.get(1));}}));
        proveedores.add(new Proveedor("aydasd","aa6a bbtb","28573672816","ccfc",new ArrayList(){{add(presupuestos.get(3));}}));
        proveedores.add(new Proveedor("aydasd","aata bbtb","28773672816","ccgc",new ArrayList(){{add(presupuestos.get(4));}}));



        Egreso unEgreso=new Egreso(new Date(),53000, new ArrayList<>(),new MetodoDePago(TipoDeMedioDePago.CHEQUE,"as"),proveedores,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioMenorPrecio());
        Mensaje cumplio=ValidadorDeOperacion.validarCustomSinBasicas(unEgreso,ValidadorDeOperacion.getValidaciones());
        Assert.assertEquals("Paso exitosamente todas las Validaciones",cumplio.getMensajeResultado());
        //no entiendo porque no da este tests
    }
}

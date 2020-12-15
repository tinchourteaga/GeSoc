package TestsEntrega3;

import Dominio.Egreso.Core.Proveedor;
import Dominio.Entidad.Direccion;
import Dominio.Entidad.DireccionPostal;
import Lugares.Ciudad;
import Lugares.Pais;
import Lugares.Provincia;

import java.time.LocalDate;

public class Test {
    DireccionPostal direc = new DireccionPostal(new Direccion("Larralde", "2454", "3", "A"), 1850, new Pais("AR","Argentina"), new Provincia("BSAS","Buenos Aires"), new Ciudad("A", "Adrogué"));
    Proveedor prov = new Proveedor("Razon Social", "28-49367281-6", direc);
    LocalDate fecha = LocalDate.of(2019, 8, 25);

    //quedo deprecado todo

    /*@org.junit.Test
    public void testValidadorNoPasaCriterio(){
        List<Presupuesto> presupuestos=new ArrayList<>();

        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(51000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(52000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(53000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(504000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(55000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(56000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        List<Proveedor> proveedores=new ArrayList();
        proveedores.add(new Proveedor("agdasd","aaaa b4bb","28493672816",direc));
        proveedores.add(new Proveedor("aydasd","aaaa bbtb","28473672816",direc));
        proveedores.add(new Proveedor("ahdasd","aaaa bcbb","28453672816",direc));
        proveedores.add(new Proveedor("asdasd","aaaa bbbb","28483672816",direc));
        proveedores.add(new Proveedor("ajdasd","aaaa bhbb","28443672816",direc));
        proveedores.add(new Proveedor("aldasd","aaaa bjbb","28433672816",direc));

        List<Integer> numeros = new ArrayList<Integer>(Arrays.asList(5,1,2,0,3,4));

        for(int i=0; i < numeros.size(); i++){
            proveedores.get(i).getPresupuestos().add(presupuestos.get(numeros.get(i)));
        }

        ValidacionPresupuestoMenor validacion1 = new ValidacionPresupuestoMenor(proveedores);
        ValidacionCompraPertenecePresupuesto validacion2 = new ValidacionCompraPertenecePresupuesto(proveedores);
        ValidacionCantidadPresupuestos validacion3 = new ValidacionCantidadPresupuestos(proveedores);
        ValidacionCriterioProveedor validacion4 = new ValidacionCriterioProveedor();

        Egreso unEgreso=new Egreso(LocalDate.now(),"Argentina", Arrays.asList(new Item(100000f,"",1)),new MetodoDePago(TipoDeMedioDePago.CHEQUE,"as"),presupuestos,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioFalla());
        Mensaje fallo=ValidadorDeOperacion.validarCustomSinBasicas(unEgreso,(new ArrayList(){{add(validacion1); add(validacion2); add(validacion3); add(validacion4);}}));
        Assert.assertEquals( new NoCumpleValidacionDeCriterioException().toString(),fallo.getMensajeResultado());
    }
    @org.junit.Test
    public void testValidadorNoPasaOperacion() {
        List<Presupuesto> presupuestos = new ArrayList<>();
        presupuestos.add(new Presupuesto(new ArrayList<>(), Arrays.asList(new Detalle(51000,"",1)), new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO, "no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(), Arrays.asList(new Detalle(52000,"",1)), new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO, "no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(), Arrays.asList(new Detalle(53000,"",1)), new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO, "no hay doc"), prov));
        List<Proveedor> proveedores = new ArrayList();
        proveedores.add(new Proveedor("asdasd", "aaaa bbbb", "28483672816", direc));
        proveedores.add(new Proveedor("agdasd", "aaaa b4bb", "28493672816", direc));
        proveedores.add(new Proveedor("aydasd", "aaaa bbtb", "28473672816", direc));

        List<Integer> numeros = new ArrayList<Integer>(Arrays.asList(0,2,1));

        for(int i=0; i < numeros.size(); i++){
            proveedores.get(i).getPresupuestos().add(presupuestos.get(numeros.get(i)));
        }

        ValidacionPresupuestoMenor validacion1 = new ValidacionPresupuestoMenor(proveedores);
        ValidacionCompraPertenecePresupuesto validacion2 = new ValidacionCompraPertenecePresupuesto(proveedores);
        ValidacionCantidadPresupuestos validacion3 = new ValidacionCantidadPresupuestos(proveedores);
        ValidacionCriterioProveedor validacion4 = new ValidacionCriterioProveedor();

        Egreso unEgreso=new Egreso(LocalDate.now(),"Argentina",Arrays.asList(new Item(51000f,"",1)),new MetodoDePago(TipoDeMedioDePago.CHEQUE,"as"),presupuestos,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioMenorPrecio());
        Mensaje fallo=ValidadorDeOperacion.validarCustomSinBasicas(unEgreso,(new ArrayList(){{add(validacion1); add(validacion2); add(validacion3); add(validacion4);}}));
        Assert.assertEquals(new NoCumpleValidacionException().toString(),fallo.getMensajeResultado());
    }

    @org.junit.Test
    public void testJerarquias() throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun, NoTengoPermisosException, NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {

        Rol roladmin= Rol.ADMINISTRADOR;
        List<CategoriaCriterio> categoriaCriterios =new ArrayList<>();
        categoriaCriterios.add(new CategoriaCriterio("descripcion1","nombre1"));
        categoriaCriterios.add(new CategoriaCriterio("descripcion2","nombre2"));
        categoriaCriterios.add(new CategoriaCriterio("descripcion3","nombre3"));
        categoriaCriterios.add(new CategoriaCriterio("descripcion4","nombre4"));
        Criterio criterioDummy=new Criterio(categoriaCriterios,"dummy","dummy");
        Criterio criterioDummyHijo=new Criterio(categoriaCriterios,"dummyHijo","dummyHijo");
        AgregarJerarquia agregarJerarquia=new AgregarJerarquia(criterioDummy,criterioDummyHijo);
        roladmin.getAcciones().add(agregarJerarquia);
        Administrador rolPrueba = new Administrador();
        Usuario unUsuario=new Usuario(rolPrueba,"pepito", "fachero","SiestaContr4senia no funca me m@deo", "41658239", "mail@mail.com");
        List<Presupuesto> presupuestos=new ArrayList<>();
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(51000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(52000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(53000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(54000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(55000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));

        List<Proveedor> proveedores=new ArrayList<>();
        proveedores.add(new Proveedor("asdasd","aaaa bbbb","28483672816",direc));
        proveedores.add(new Proveedor("agdasd","aaaa b4bb","28493672816",direc));
        proveedores.add(new Proveedor("aydasd","aaaa bbtb","28473672816",direc));
        proveedores.add(new Proveedor("aydasd","aa6a bbtb","28573672816",direc));
        proveedores.add(new Proveedor("aydasd","aata bbtb","28773672816",direc));

        List<Integer> numeros = new ArrayList<Integer>(Arrays.asList(0,2,1,3,4));

        for(int i=0; i < numeros.size(); i++){
            proveedores.get(i).getPresupuestos().add(presupuestos.get(numeros.get(i)));
        }

        Egreso unEgreso=new Egreso(LocalDate.now(),"Argentina",Arrays.asList(new Item(53000f,"",1)),new MetodoDePago(TipoDeMedioDePago.CHEQUE,"as"),presupuestos,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioMenorPrecio());
        roladmin.realizarAccion(agregarJerarquia);
        Assert.assertEquals(criterioDummyHijo, criterioDummy.getHijos().get(0));
    }

    @org.junit.Test
    public void testValidadorPasaOperacion(){
        List<Presupuesto> presupuestos=new ArrayList<>();
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(51000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(52000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(53000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(54000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));
        presupuestos.add(new Presupuesto(new ArrayList<>(),Arrays.asList(new Detalle(55000,"",1)),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc"), prov));

        List<Proveedor> proveedores=new ArrayList();
        proveedores.add(new Proveedor("asdasd","aaaa bbbb","28483672816",direc));
        proveedores.add(new Proveedor("agdasd","aaaa b4bb","28493672816",direc));
        proveedores.add(new Proveedor("aydasd","aaaa bbtb","28473672816",direc));
        proveedores.add(new Proveedor("aydasd","aa6a bbtb","28573672816",direc));
        proveedores.add(new Proveedor("aydasd","aata bbtb","28773672816",direc));

        List<Integer> numeros = new ArrayList<Integer>(Arrays.asList(0,2,1,3,4));

        for(int i=0; i < numeros.size(); i++){
            proveedores.get(i).getPresupuestos().add(presupuestos.get(numeros.get(i)));
        }

        Egreso unEgreso=new Egreso(LocalDate.now(),"Argentina",Arrays.asList(new Item(53000f,"",1)),new MetodoDePago(TipoDeMedioDePago.CHEQUE,"as"),presupuestos,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioMenorPrecio());
        Mensaje cumplio=ValidadorDeOperacion.validarCustomSinBasicas(unEgreso,ValidadorDeOperacion.getValidaciones());
        Assert.assertEquals("Paso exitosamente todas las Validaciones",cumplio.getMensajeResultado());
        //no entiendo porque no da este tests
    }

     */
}

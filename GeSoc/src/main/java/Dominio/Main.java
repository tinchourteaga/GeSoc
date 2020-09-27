package Dominio;

import API.Vinculacion.ControllerVinculacion;
import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Ingreso.Ingreso;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        /*ValidadorDeOperacion.setEstrategia(new EjecucionAutomatica(19,33));

        Direccion direc = new Direccion("Larralde", "2454", "3");

        List<Presupuesto> presupuestos=new ArrayList<>();
        presupuestos.add(new Presupuesto(new ArrayList<>(),51000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),52000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        presupuestos.add(new Presupuesto(new ArrayList<>(),53000,new ArrayList<>(),new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hay doc")));
        List<Proveedor> proveedores=new ArrayList();
        proveedores.add(new Proveedor("asdasd","aaaa bbbb","28483672816",direc));
        proveedores.add(new Proveedor("agdasd","aaaa b4bb","28493672816",direc));
        proveedores.add(new Proveedor("aydasd","aaaa bbtb","28473672816",direc));

        proveedores.forEach(prov -> prov.getPresupuestos().addAll(presupuestos));

        Egreso unEgreso=new Egreso(new Date(),"Argentina", 51000,new ArrayList<>(),new MetodoDePago(TipoDeMedioDePago.CHEQUE,"as"),proveedores,new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"no hubo documento"),new CriterioMenorPrecio());

        ValidadorDeOperacion.getValidaciones().clear();
        ValidadorDeOperacion.getValidaciones().addAll((new ArrayList(){{add(new ValidacionPresupuestoMenor(proveedores)); add(new ValidacionCompraPertenecePresupuesto(proveedores)); add(new ValidacionCantidadPresupuestos(proveedores)); add(new ValidacionCriterioProveedor());}}));
        //ValidadorDeOperacion.getRepositorio().getAllElementos().addAll((new ArrayList(){{add(new ValidacionPresupuestoMenor(proveedores)); add(new ValidacionCompraPertenecePresupuesto(proveedores)); add(new ValidacionCantidadPresupuestos(proveedores)); add(new ValidacionCriterioProveedor());}}));

        ValidadorDeOperacion.validarPorEstrategia(unEgreso);
        System.out.println(unEgreso.isEstaVerificada());


        System.out.println(unEgreso.isEstaVerificada());*/

        //otro test

        /*
        ControllerMercadoLibre varController = ControllerMercadoLibre.getControllerMercadoLibre();

        System.out.println("Ingrese un pais: ");
        Scanner s = new Scanner(System.in);
        String nombrePais = s.nextLine();
        System.out.println(varController.getPais(nombrePais));

        try {
            List<ProvinciaDTO> provinciaDTOS = varController.obtenerLasProviciasDeUnPais(varController.getPais(nombrePais).getId());

            provinciaDTOS.forEach(x->System.out.println(x.getName() + "ID:" + x.getId()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Ingrese un provincia: ");
        Scanner w = new Scanner(System.in);
        String nombreProv = w.nextLine();

        try {
            List<CiudadDTO> ciudadesDTOS = varController.obtenerLasCiudadesDeUnaProvincia(nombreProv);

            ciudadesDTOS.forEach(x->System.out.println(x.getName()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    */

        //debug time
        //ControllerMercadoLibre varController = ControllerMercadoLibre.getControllerMercadoLibre();

        //varController.getPais("Argentina");

        //Servidor.levantarServidor();

        Ingreso unIngreso = new Ingreso("Argentina",1557, LocalDate.parse("2014-02-14"),"pruebita",new ArrayList<>());
        Ingreso otroIngreso = new Ingreso("Brasil",1357, LocalDate.parse("2020-02-07"),"pruebita2",new ArrayList<>());

        List<Ingreso> ingresos = new ArrayList<>();

        ingresos.add(unIngreso);
        ingresos.add(otroIngreso);

        ControllerVinculacion.obtenerInstacia().vincular(new ArrayList<>(), ingresos,new ArrayList<>(),new ArrayList<>());

        //ControllerVinculacion.obtenerInstacia().vincular(new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
    }
}
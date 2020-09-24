package Dominio;

import Servidor.Servidor;

public class Main {

    public static void main(String[] args) {
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

        Servidor.levantarServidor();
    }
}
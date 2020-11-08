package Dominio;

import API.ML.ControllerMercadoLibre;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
    /* //PROCESO VINCULACIÓN - ENTREGA 4.2
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
    */


        //corro la base
        /*try {
            AddData.main(args);
        } catch (IOException | ExcepcionNumero | ExcepcionContraseniaComun | ExcepcionCaracterEspecial | ExcepcionLongitud e) {
            e.printStackTrace();//no deberias pasar por aca, pero tincho no se banca tener un try and catch donde corresponde
        }*/

        //cargo cosas de ML
        try {
            ControllerMercadoLibre.getControllerMercadoLibre().inicializarBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //corro el server
        //Servidor.levantarServidor();


        /*String mailUsuario, usuario, contrasenia;

        // email ID of Recipient.
        String recipient = "covellojorge@gmail.com";

        // email ID of  Sender.
        String sender = "orggesoc@gmail.com";

        // using host as localhost
        String host = "192.168.0.5";

        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);

        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);


            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
        try {
            message.setFrom(new InternetAddress(sender));
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Set To Field: adding recipient's email to from field.
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Set Subject: subject of the email
        try {
            message.setSubject("Usuario y contrasenia");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // set body of the email.
        try {
            message.setText("Su usario es: " + "usuario");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setText("Su password es: " + "contrasenia");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Send email.
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Mail enviado...");*/



    }
}
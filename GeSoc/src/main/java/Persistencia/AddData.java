package Persistencia;

import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioMenorPrecio;
import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Egreso.Core.*;
import Dominio.Entidad.Categorias.Categoria;
import Dominio.Entidad.Categorias.TipoCategoria;
import Dominio.Entidad.*;
import Dominio.Ingreso.Ingreso;
import Dominio.Rol.Administrador;
import Dominio.Rol.Estandar;
import Dominio.Rol.Rol;
import Dominio.Usuario.Usuario;
import Lugares.Ciudad;
import Lugares.Pais;
import Lugares.Provincia;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.DAO.DAOUsuario;
import Persistencia.Repos.Repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//Aca metemos las cosas que queremos persistir desde un comienzo; algo con lo que poder trabajar apenas se levanta el sistema.
public class AddData {

    public static void main(String[] args) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();


        // if (results.size() == 0) {
        //no hay nada cargado

        //creo roles
        Rol admin = new Administrador();
        Rol estandar = new Estandar();
        Rol revisor1 = new Estandar();//hay que agregarle todas las operaciones de la org para que las revise
        Rol revisor2 = new Estandar();//hay que agregarle todas las operaciones de la org para que las revise

        //creo usuarios
        Usuario usuarioAdmin = new Usuario(admin, "martin", "urteaga", "M@rtin.98", "41589363", "martin@hotmail.com");
        usuarioAdmin.setPersona();

        Usuario alejandro = new Usuario(revisor1, "Alejandro", "Roco", "*_aroco20!-?", "41589363", "aroco@hotmail.com");
        alejandro.setPersona();

        Usuario rocio = new Usuario(estandar, "Rocio", "Rojas", "*-_rrojas!?", "41589363", "rrojas@hotmail.com");
        rocio.setPersona();

        Usuario julieta = new Usuario(revisor2, "Julieta", "Azul", "!-*jazul_!?", "41589363", "jazul@hotmail.com");
        julieta.setPersona();


        List<Usuario> usuariosAPersistir = new ArrayList<>();
        usuariosAPersistir.add(usuarioAdmin);
        usuariosAPersistir.add(alejandro);
        usuariosAPersistir.add(rocio);
        usuariosAPersistir.add(julieta);


        //persistoUsuarios
        DAO DAOUsuario = new DAOUsuario();
        Repositorio repoUsuario = new Repositorio(DAOUsuario);
        usuariosAPersistir.forEach(us -> repoUsuario.agregar(us));

        Repositorio repoPaises = new Repositorio(new DAOBBDD<Pais>(Pais.class));
        List<Pais> todosLosPaises = repoPaises.getTodosLosElementos();
        Pais argentina = todosLosPaises.stream().filter(p -> p.getName().equals("Argentina")).collect(Collectors.toList()).get(0);
        Pais eeuu = todosLosPaises.stream().filter(p -> p.getName().equals("Venezuela")).collect(Collectors.toList()).get(0);
        Pais mexico = todosLosPaises.stream().filter(p -> p.getName().equals("Mexico")).collect(Collectors.toList()).get(0);


        Repositorio repoProvincias = new Repositorio(new DAOBBDD<Provincia>(Provincia.class));
        List<Provincia> todosLasProvincias = repoProvincias.getTodosLosElementos();
        Provincia caba = todosLasProvincias.stream().filter(prov -> prov.getName().equals("Capital Federal")).collect(Collectors.toList()).get(0);
        Provincia provCiudadMexico = todosLasProvincias.stream().filter(prov -> prov.getName().equals("Distrito Federal")).collect(Collectors.toList()).get(0);
        Provincia nuevaYork = todosLasProvincias.stream().filter(prov -> prov.getName().equals("Nueva Esparta")).collect(Collectors.toList()).get(0);


        Repositorio repoCiudades = new Repositorio(new DAOBBDD<Ciudad>(Ciudad.class));
        List<Ciudad> todasLasCiudades = repoCiudades.getTodosLosElementos();
        Ciudad cabac = todasLasCiudades.stream().filter(ciu -> ciu.getName().equals("Capital Federal")).collect(Collectors.toList()).get(0);
        Ciudad brooklyn = todasLasCiudades.stream().filter(ciu -> ciu.getName().equals("Marcano")).collect(Collectors.toList()).get(0);
        Ciudad ciudadAUMexico = todasLasCiudades.stream().filter(ciu -> ciu.getName().equals("Tláhuac")).collect(Collectors.toList()).get(0);


        //creo direcciones es calle altura piso y depto
        Direccion direccion1 = new Direccion("Av. Avellaneda", "231", "", "");
        Direccion direccion2 = new Direccion("Av Rivadavia", "3543", "", "");
        Direccion direccion3 = new Direccion("Av. Medrano", "32", "", "");
        Direccion direccion4 = new Direccion("Av. Pedro Goyena", "1213", "", "");
        Direccion direccion5 = new Direccion("Av Rivadavia", "8876", "", "");
        Direccion direccion6 = new Direccion("Av.Rivadavia", "4663", "", "");
        Direccion direccion7 = new Direccion("Av La Plata", "1234", "", "");
        Direccion direccion8 = new Direccion("Emilio Mitre", "5664", "", "");
        Direccion direccion9 = new Direccion("Castro Barro", "765", "", "");
        Direccion direccion10 = new Direccion("Del Barco Centenera", "809", "", "");
        Direccion direccion11 = new Direccion("Av. Segurola", "6323", "", "");
        Direccion direccion12 = new Direccion("Av. Asamblea", "7859", "", "");


        //creo direccionesPostales Direccion, cp, pais, provincia,ciudad
        DireccionPostal direccionPostal1 = new DireccionPostal(direccion1, 1, argentina, caba, cabac);
        DireccionPostal direccionPostal2 = new DireccionPostal(direccion2, 2, argentina, caba, cabac);
        DireccionPostal direccionPostal3 = new DireccionPostal(direccion3, 3, argentina, caba, cabac);
        DireccionPostal direccionPostal4 = new DireccionPostal(direccion4, 4, argentina, caba, cabac);
        DireccionPostal direccionPostal5 = new DireccionPostal(direccion5, 5, argentina, caba, cabac);
        DireccionPostal direccionPostal6 = new DireccionPostal(direccion6, 6, argentina, caba, cabac);
        DireccionPostal direccionPostal7 = new DireccionPostal(direccion7, 7, argentina, caba, cabac);
        DireccionPostal direccionPostal8 = new DireccionPostal(direccion8, 8, argentina, caba, cabac);
        DireccionPostal direccionPostal9 = new DireccionPostal(direccion9, 9, argentina, caba, cabac);
        DireccionPostal direccionPostal10 = new DireccionPostal(direccion10, 10, argentina, caba, cabac);
        DireccionPostal direccionPostal11 = new DireccionPostal(direccion11, 11, argentina, caba, cabac);
        DireccionPostal direccionPostal12 = new DireccionPostal(direccion12, 12, argentina, caba, cabac);

        //creo proveedores String nombre, String apellido, String dni, DireccionPostal unaDireccion
        Proveedor proveedor1 = new Proveedor("Pinturerías Serrentino", "", "", direccionPostal1);
        Proveedor proveedor2 = new Proveedor("Edesur", "", "", direccionPostal2);
        Proveedor proveedor3 = new Proveedor("Metrogas", "", "", direccionPostal3);
        Proveedor proveedor4 = new Proveedor("Mitoas SA", "", "", direccionPostal4);
        Proveedor proveedor5 = new Proveedor("Ingeniería Comercial SRL", "", "", direccionPostal5);
        Proveedor proveedor6 = new Proveedor("Corralón Laprida SRL", "", "", direccionPostal6);
        Proveedor proveedor7 = new Proveedor("Telas ZN", "", "", direccionPostal7);
        Proveedor proveedor8 = new Proveedor("Pinturerías REX", "", "", direccionPostal8);
        Proveedor proveedor9 = new Proveedor("Pinturerías San Jorge", "", "", direccionPostal9);
        Proveedor proveedor10 = new Proveedor("La casa del Audio ", "", "", direccionPostal10);
        Proveedor proveedor11 = new Proveedor("Garbarino", "", "", direccionPostal11);
        Proveedor proveedor12 = new Proveedor("Corralón San Juan SRL", "", "", direccionPostal12);


        List<Proveedor> proveedoresAPersistir = new ArrayList<>();
        proveedoresAPersistir.add(proveedor1);
        proveedoresAPersistir.add(proveedor2);
        proveedoresAPersistir.add(proveedor3);
        proveedoresAPersistir.add(proveedor4);
        proveedoresAPersistir.add(proveedor5);
        proveedoresAPersistir.add(proveedor6);
        proveedoresAPersistir.add(proveedor7);
        proveedoresAPersistir.add(proveedor8);
        proveedoresAPersistir.add(proveedor9);
        proveedoresAPersistir.add(proveedor10);
        proveedoresAPersistir.add(proveedor11);
        proveedoresAPersistir.add(proveedor12);


        //persistoProvedores
        DAO DAOProveedor = new DAOBBDD<Proveedor>(Proveedor.class);
        Repositorio repoProveedor = new Repositorio(DAOProveedor);
        proveedoresAPersistir.forEach(prov -> repoProveedor.agregar(prov));


        //genero y persisto actividades
        Sector agropecuario = new Sector(new ArrayList<>(), "AGROPECUARIO", "Sector agropecuario");
        Categoria microArgo = new Categoria(agropecuario, TipoCategoria.MICRO, 5, 17260000f);
        Categoria pequeniaAgro = new Categoria(agropecuario, TipoCategoria.PEQUENIA, 10, 71960000f);
        Categoria mediana1Agro = new Categoria(agropecuario, TipoCategoria.MEDIANA_TRAMO_1, 50, 426720000f);
        Categoria mediana2Agro = new Categoria(agropecuario, TipoCategoria.MEDIANA_TRAMO_2, 215, 676810000f);
        agropecuario.agregarCategoria(microArgo);
        agropecuario.agregarCategoria(pequeniaAgro);
        agropecuario.agregarCategoria(mediana1Agro);
        agropecuario.agregarCategoria(mediana2Agro);

        Sector iYm = new Sector(new ArrayList<>(), "INDUSTRIA Y MINERIA", "Sector de industria y mineria");
        Categoria microiYm = new Categoria(iYm, TipoCategoria.MICRO, 15, 33920000f);
        Categoria pequeniaiYm = new Categoria(iYm, TipoCategoria.PEQUENIA, 60, 243290000f);
        Categoria mediana1iYm = new Categoria(iYm, TipoCategoria.MEDIANA_TRAMO_1, 235, 1651750000f);
        Categoria mediana2iYm = new Categoria(iYm, TipoCategoria.MEDIANA_TRAMO_2, 655, 2540380000f);
        iYm.agregarCategoria(microiYm);
        iYm.agregarCategoria(pequeniaiYm);
        iYm.agregarCategoria(mediana1iYm);
        iYm.agregarCategoria(mediana2iYm);


        Sector comercio = new Sector(new ArrayList<>(), "COMERCIO", "Sector comercio");
        Categoria microcomercio = new Categoria(comercio, TipoCategoria.MICRO, 7, 36320000f);
        Categoria pequeniacomercio = new Categoria(comercio, TipoCategoria.PEQUENIA, 35, 247200000f);
        Categoria mediana1comercio = new Categoria(comercio, TipoCategoria.MEDIANA_TRAMO_1, 125, 1821760000f);
        Categoria mediana2comercio = new Categoria(comercio, TipoCategoria.MEDIANA_TRAMO_2, 345, 2602540000f);
        comercio.agregarCategoria(microcomercio);
        comercio.agregarCategoria(pequeniacomercio);
        comercio.agregarCategoria(mediana1comercio);
        comercio.agregarCategoria(mediana2comercio);

        Sector servicios = new Sector(new ArrayList<>(), "SERVICIOS", "Sector de servicios");
        Categoria microservicios = new Categoria(servicios, TipoCategoria.MICRO, 7, 9900000f);
        Categoria pequeniaservicios = new Categoria(servicios, TipoCategoria.PEQUENIA, 30, 59710000f);
        Categoria mediana1servicios = new Categoria(servicios, TipoCategoria.MEDIANA_TRAMO_1, 165, 494200000f);
        Categoria mediana2servicios = new Categoria(servicios, TipoCategoria.MEDIANA_TRAMO_2, 535, 705790000f);
        servicios.agregarCategoria(microservicios);
        servicios.agregarCategoria(pequeniaservicios);
        servicios.agregarCategoria(mediana1servicios);
        servicios.agregarCategoria(mediana2servicios);

        Sector construccion = new Sector(new ArrayList<>(), "CONSTRUCCION", "Sector de construcciones");
        Categoria microconstruccion = new Categoria(construccion, TipoCategoria.MICRO, 12, 19450000f);
        Categoria pequeniaconstruccion = new Categoria(construccion, TipoCategoria.PEQUENIA, 45, 115370000f);
        Categoria mediana1construccion = new Categoria(construccion, TipoCategoria.MEDIANA_TRAMO_1, 200, 643710000f);
        Categoria mediana2construccion = new Categoria(construccion, TipoCategoria.MEDIANA_TRAMO_2, 590, 965460000f);
        construccion.agregarCategoria(microconstruccion);
        construccion.agregarCategoria(pequeniaconstruccion);
        construccion.agregarCategoria(mediana1construccion);
        construccion.agregarCategoria(mediana2construccion);


        Direccion direccionEntidad1 = new Direccion("Av. Medrano", "971", "", "");
        Direccion direccionEntidad2 = new Direccion("Liberty Ave", " 720", "", "");
        Direccion direccionEntidad3 = new Direccion("Roberto Gayol", "55", "", "");
        Direccion direccionEntidad4 = new Direccion("Jerónimo Salguero", "2800", "", "");

        DireccionPostal direccionPostalEntidad1 = new DireccionPostal(direccionEntidad1, 1111, argentina, caba, cabac);
        DireccionPostal direccionPostalEntidad2 = new DireccionPostal(direccionEntidad2, 2222, eeuu, nuevaYork, brooklyn);
        DireccionPostal direccionPostalEntidad3 = new DireccionPostal(direccionEntidad3, 3333, mexico, provCiudadMexico, ciudadAUMexico);
        DireccionPostal direccionPostalEntidad4 = new DireccionPostal(direccionEntidad4, 4444, argentina, caba, cabac);


        //genero empresas
        Empresa tipoEntidad1 = new Empresa();
        tipoEntidad1.setActividad(construccion);
        tipoEntidad1.setCantidadPersonal(150);
        tipoEntidad1.setPromedioVentasAnuales((float) 600000000.00);
        tipoEntidad1.setCuit("30-15269857-2");
        tipoEntidad1.setRazonSocial("EAAF BA");
        tipoEntidad1.setCodigoDeInscripcion("");
        tipoEntidad1.setDireccionPostal(direccionPostalEntidad1);
        tipoEntidad1.determinarCategoria();

        Empresa tipoEntidad2 = new Empresa();
        tipoEntidad2.setActividad(construccion);
        tipoEntidad2.setCantidadPersonal(580);
        tipoEntidad2.setPromedioVentasAnuales((float) 960000000.00);
        tipoEntidad2.determinarCategoria();
        tipoEntidad2.setCuit("30-15789655-7");
        tipoEntidad2.setRazonSocial("EAAF NY");
        tipoEntidad2.setCodigoDeInscripcion("");
        tipoEntidad2.setDireccionPostal(direccionPostalEntidad2);

        Empresa tipoEntidad3 = new Empresa();
        tipoEntidad3.setActividad(construccion);
        tipoEntidad3.setCantidadPersonal(240);
        tipoEntidad3.setPromedioVentasAnuales((float) 643710000.00);
        tipoEntidad3.determinarCategoria();
        tipoEntidad3.setCuit("30-77896583-9");
        tipoEntidad3.setRazonSocial("EAAF M");
        tipoEntidad3.setCodigoDeInscripcion("");
        tipoEntidad3.setDireccionPostal(direccionPostalEntidad3);

        Empresa tipoEntidad4 = new Empresa();
        tipoEntidad4.setActividad(servicios);
        tipoEntidad4.setCantidadPersonal(8);
        tipoEntidad4.setPromedioVentasAnuales((float) 8000000.00);
        tipoEntidad4.determinarCategoria();
        tipoEntidad4.setCuit("30-25888897-8");
        tipoEntidad4.setRazonSocial("Surcos CS");
        tipoEntidad4.setCodigoDeInscripcion("");
        tipoEntidad4.setDireccionPostal(direccionPostalEntidad4);


        //Genero las entidades
        Entidad entidad1 = new EntidadJuridica("Oficina central de Argentina", "", tipoEntidad1);
        Entidad entidad2 = new EntidadJuridica("Oficina central de Nueva York", "", tipoEntidad2);
        Entidad entidad3 = new EntidadJuridica("Oficina central de Mexico", "", tipoEntidad3);
        EntidadJuridica entidad4 = new EntidadJuridica("Surcos", "", tipoEntidad4);
        Entidad entidad5 = new EntidadBase("Andhes", "", entidad4);


        //armo los ingresos de cada empresa
        Ingreso ingreso1 = new Ingreso("Peso Argentino", 20000, LocalDate.parse("2020-02-25"), "Donación de terceros", new ArrayList());
        Ingreso ingreso2 = new Ingreso("Peso Argentino", 10000, LocalDate.parse("2020-05-02"), "Donación de Rimoli SA", new ArrayList());
        Ingreso ingreso3 = new Ingreso("Peso Argentino", 980000, LocalDate.parse("2020-08-03"), "Donación de Gran Imperio", new ArrayList());
        Ingreso ingreso4 = new Ingreso("Peso Argentino", 10000, LocalDate.parse("2020-05-01"), "Donación Gabino SRL", new ArrayList());

        ingreso1.setEntidad(entidad1);
        ingreso2.setEntidad(entidad1);
        ingreso3.setEntidad(entidad1);
        ingreso4.setEntidad(entidad4);


        entidad1.agregarIngreso(ingreso1);
        entidad1.agregarIngreso(ingreso2);
        entidad1.agregarIngreso(ingreso3);
        entidad4.agregarIngreso(ingreso4);


        Repositorio repoEntidad = new Repositorio(new DAOBBDD<Entidad>(Entidad.class));
        repoEntidad.agregar(entidad1);
        repoEntidad.agregar(entidad2);
        repoEntidad.agregar(entidad3);
        repoEntidad.agregar(entidad4);
        repoEntidad.agregar(entidad5);

        Repositorio repoIngresos = new Repositorio(new DAOBBDD<Ingreso>(Ingreso.class));
        repoIngresos.agregar(ingreso1);
        repoIngresos.agregar(ingreso2);
        repoIngresos.agregar(ingreso3);
        repoIngresos.agregar(ingreso4);


        //creo los items
        Item item1=new Item(9625f,"PINTURA Z10 LATEX SUPERCUBRITIVO 20L",1);
        item1.setTipo("Producto");
        Item item2=new Item(6589.4f,"PINTURA LOXON FTES IMPERMEABILIZANTE 10L",1);
        item2.setTipo("Producto");
        Item item3=new Item(3738.29f,"PINTURA BRIKOL PISOS NEGRO 4L",1);
        item3.setTipo("Producto");
        Item item4=new Item(2100f,"FACTURA SERVICIO DE LUZ PERIODO JUNIO 2020",1);
        item4.setTipo("Servicio");
        Item item5=new Item(3500f,"FACTURA SERVICIO DE GAS PERIODO JUNIO 2020",1);
        item5.setTipo("Servicio");
        Item item6=new Item(4500f,"PAVA ELECTRICA SMARTLIFE 1,5 LTS 1850W",3);
        item6.setTipo("Producto");
        Item item7=new Item(6300f,"CAFETERA SMARTLIFE 1095 ACERO INOX.",2);
        item7.setTipo("Producto");
        Item item8=new Item(8500f,"TELEFONOS INALAMBRICOS MOTOROLA DUO BLANCO",2);
        item8.setTipo("Producto");
        Item item9=new Item(704.4f,"CEMENTO LOMA NEGRA",10);
        item9.setTipo("Producto");
        Item item10=new Item(3100f,"ARENA FINA EN BOLSÓN X M30",5);
        item10.setTipo("Producto");
        Item item11=new Item(891f,"HIERRO ACINDAR",4);
        item11.setTipo("Producto");
        Item item12=new Item(227f,"BLOQUE LADRILLO CEMENTO",800);
        item12.setTipo("Producto");
        Item item13=new Item(250f,"BLOQUE LADRILLO CEMENTO",800);
        item13.setTipo("Producto");
        Item item14=new Item(1100f,"FACTURA SERVICIO DE LUZ PERIODO JUNIO 2020",1);
        item14.setTipo("Servicio");
        Item item15=new Item(800f,"FACTURA SERVICIO DE GAS PERIODO JUNIO 2020",1);
        item15.setTipo("Servicio");
        Item item16=new Item(4200f,"CORTINAS BLACKOUT VINILICO 2 PAÑOS",5);
        item16.setTipo("Producto");


        MetodoDePago metodo1= new MetodoDePago(TipoDeMedioDePago.TARJETA_CREDITO, "Tarjeta de Crédito - 3 pagos s/i - N° tarjeta: 4509 9535 6623 3704 ");
        List<Item> items1=new ArrayList();
        items1.add(item1);
        items1.add(item2);
        items1.add(item3);
        List<Presupuesto> presupuestos1= new ArrayList();
        CriterioSeleccionProveedor criterio1= new CriterioMenorPrecio();
        DocumentoComercial unDocumento1= new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"No se presentaron datos");

        MetodoDePago metodo2= new MetodoDePago(TipoDeMedioDePago.EFECTIVO, "Pago en efectivo");
        List<Item > items2=new ArrayList();
        items2.add(item4);
        List<Presupuesto> presupuestos2= new ArrayList();
        DocumentoComercial unDocumento2= new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"No se presentaron datos");

        MetodoDePago metodo3= metodo2;
        List<Item > items3=new ArrayList();
        items3.add(item5);
        List<Presupuesto> presupuestos3= new ArrayList();
        DocumentoComercial unDocumento3= new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"No se presentaron datos");

        MetodoDePago metodo4= new MetodoDePago(TipoDeMedioDePago.TARJETA_DEBITO, "Tarjeta de Débito - N° tarjeta: 5031 7557 3453 0604 ");
        List<Item > items4=new ArrayList();
        items4.add(item6);
        items4.add(item7);
        List<Presupuesto> presupuestos4= new ArrayList();
        DocumentoComercial unDocumento4= new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"No se presentaron datos");

        MetodoDePago metodo5= metodo2;
        List<Item > items5=new ArrayList();
        items5.add(item8);
        List<Presupuesto> presupuestos5= new ArrayList();
        DocumentoComercial unDocumento5= new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"No se presentaron datos");

        MetodoDePago metodo6= metodo2;
        List<Item > items6=new ArrayList();
        items6.add(item9);
        items6.add(item10);
        items6.add(item11);
        items6.add(item12);
        List<Presupuesto> presupuestos6= new ArrayList();
        DocumentoComercial unDocumento6= new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"No se presentaron datos");


        MetodoDePago metodo7= metodo2;
        List<Item > items7=new ArrayList();
        items7.add(item13);
        List<Presupuesto> presupuestos7= new ArrayList();
        DocumentoComercial unDocumento7= new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"No se presentaron datos");

        MetodoDePago metodo8= metodo2;
        List<Item > items8=new ArrayList();
        items8.add(item14);
        List<Presupuesto> presupuestos8= new ArrayList();
        DocumentoComercial unDocumento8= new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"No se presentaron datos");

        MetodoDePago metodo9= metodo2;
        List<Item > items9=new ArrayList();
        items9.add(item15);
        List<Presupuesto> presupuestos9= new ArrayList();
        DocumentoComercial unDocumento9= new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"No se presentaron datos");

        MetodoDePago metodo10= metodo2;
        List<Item > items10=new ArrayList();
        items10.add(item16);
        List<Presupuesto> presupuestos10= new ArrayList();
        DocumentoComercial unDocumento10= new DocumentoComercial(TipoDocumentoComercial.SIN_DOCUMENTO,"No se presentaron datos");




        //cargo presupuestos son 8

        List<Criterio> criterios1= new ArrayList();
        List<Detalle> detalles1= new ArrayList<>();

        List<Criterio> criterios2= new ArrayList();
        List<Detalle> detalles2= new ArrayList<>();

        List<Criterio> criterios3= new ArrayList();
        List<Detalle> detalles3= new ArrayList<>();

        List<Criterio> criterios4= new ArrayList();
        List<Detalle> detalles4= new ArrayList<>();

        List<Criterio> criterios5= new ArrayList();
        List<Detalle> detalles5= new ArrayList<>();

        List<Criterio> criterios6= new ArrayList();
        List<Detalle> detalles6= new ArrayList<>();

        List<Criterio> criterios7= new ArrayList();
        List<Detalle> detalles7= new ArrayList<>();

        List<Criterio> criterios8= new ArrayList();
        List<Detalle> detalles8= new ArrayList<>();

        Presupuesto presupuesto1= new Presupuesto(criterios1, 9900.8f+7200f+4350.8f, detalles1, new DocumentoComercial (TipoDocumentoComercial.SIN_DOCUMENTO,""), proveedor8);
        Presupuesto presupuesto2= new Presupuesto(criterios2, 9900.8f+7200f+4350.8f, detalles2, new DocumentoComercial (TipoDocumentoComercial.SIN_DOCUMENTO,""), proveedor9);
        Presupuesto presupuesto3= new Presupuesto(criterios3, 9900.8f+7200f+4350.8f, detalles3, new DocumentoComercial (TipoDocumentoComercial.SIN_DOCUMENTO,""), proveedor1);
        Presupuesto presupuesto4= new Presupuesto(criterios4, 9900.8f+7200f+4350.8f, detalles4, new DocumentoComercial (TipoDocumentoComercial.SIN_DOCUMENTO,""), proveedor10);
        Presupuesto presupuesto5= new Presupuesto(criterios5, 9900.8f+7200f+4350.8f, detalles5, new DocumentoComercial (TipoDocumentoComercial.SIN_DOCUMENTO,""), proveedor11);
        Presupuesto presupuesto6= new Presupuesto(criterios6, 9900.8f+7200f+4350.8f, detalles6, new DocumentoComercial (TipoDocumentoComercial.SIN_DOCUMENTO,""), proveedor5);
        Presupuesto presupuesto7= new Presupuesto(criterios7, 9900.8f+7200f+4350.8f, detalles7, new DocumentoComercial (TipoDocumentoComercial.SIN_DOCUMENTO,""), proveedor12);
        Presupuesto presupuesto8= new Presupuesto(criterios8, 9900.8f+7200f+4350.8f, detalles8, new DocumentoComercial (TipoDocumentoComercial.SIN_DOCUMENTO,""), proveedor6);


        //cargo egresos son 10

        List<Egreso>egresosAPersistir=new ArrayList();
        Egreso egreso1= new Egreso(LocalDate.parse("2020-03-10"), "Argentina", 9625+6589.4+3738.29, items1, metodo1, presupuestos1,  unDocumento1, criterio1);
        Egreso egreso2= new Egreso(LocalDate.parse("2020-07-08"), "Argentina", 9625+6589.4+3738.29, items2, metodo2, presupuestos2,  unDocumento2, criterio1);
        Egreso egreso3= new Egreso(LocalDate.parse("2020-07-09"), "Argentina", 9625+6589.4+3738.29, items3, metodo3, presupuestos3,  unDocumento3, criterio1);
        Egreso egreso4= new Egreso(LocalDate.parse("2020-08-03"), "Argentina", 9625+6589.4+3738.29, items4, metodo4, presupuestos4,  unDocumento4, criterio1);
        Egreso egreso5= new Egreso(LocalDate.parse("2020-09-27"), "Argentina", 9625+6589.4+3738.29, items5, metodo5, presupuestos5,  unDocumento5, criterio1);
        Egreso egreso6= new Egreso(LocalDate.parse("2020-10-01"), "Argentina", 9625+6589.4+3738.29, items6, metodo6, presupuestos6,  unDocumento6, criterio1);
        Egreso egreso7= new Egreso(LocalDate.parse("2020-10-05"), "Argentina", 9625+6589.4+3738.29, items7, metodo7, presupuestos7,  unDocumento7, criterio1);
        Egreso egreso8= new Egreso(LocalDate.parse("2020-07-10"), "Argentina", 9625+6589.4+3738.29, items8, metodo8, presupuestos8,  unDocumento8, criterio1);
        Egreso egreso9= new Egreso(LocalDate.parse("2020-07-10"), "Argentina", 9625+6589.4+3738.29, items9, metodo9, presupuestos9,  unDocumento9, criterio1);
        Egreso egreso10= new Egreso(LocalDate.parse("2020-09-25"), "Argentina", 9625+6589.4+3738.29, items10, metodo10, presupuestos10,  unDocumento10, criterio1);





        egresosAPersistir.add(egreso1);
        egresosAPersistir.add(egreso2);
        egresosAPersistir.add(egreso3);
        egresosAPersistir.add(egreso4);
        egresosAPersistir.add(egreso5);
        egresosAPersistir.add(egreso6);
        egresosAPersistir.add(egreso7);
        egresosAPersistir.add(egreso8);
        egresosAPersistir.add(egreso9);
        egresosAPersistir.add(egreso10);

        Repositorio repoEgresos= new Repositorio(new DAOBBDD<Egreso>(Egreso.class));
        egresosAPersistir.forEach(e->repoEgresos.agregar(e));































        //}
    }
}


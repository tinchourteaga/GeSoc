package Persistencia;

import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
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
            Rol estandar=new Estandar();
            Rol revisor1= new Estandar();//hay que agregarle todas las operaciones de la org para que las revise
            Rol revisor2= new Estandar();//hay que agregarle todas las operaciones de la org para que las revise

            //creo usuarios
            Usuario usuarioAdmin = new Usuario(admin, "martin", "urteaga", "M@rtin.98", "41589363","martin@hotmail.com");
            usuarioAdmin.setPersona();

            Usuario alejandro = new Usuario(revisor1, "Alejandro", "Roco", "*_aroco20!-?", "41589363","aroco@hotmail.com");
            alejandro.setPersona();

            Usuario rocio = new Usuario(estandar, "Rocio", "Rojas", "*-_rrojas!?", "41589363","rrojas@hotmail.com");
            rocio.setPersona();

            Usuario julieta = new Usuario(revisor2, "Julieta", "Azul", "!-*jazul_!?", "41589363","jazul@hotmail.com");
            julieta.setPersona();



            List<Usuario> usuariosAPersistir=new ArrayList<>();
            usuariosAPersistir.add(usuarioAdmin);
            usuariosAPersistir.add(alejandro);
            usuariosAPersistir.add(rocio);
            usuariosAPersistir.add(julieta);


            //persistoUsuarios
            DAO DAOUsuario = new DAOUsuario();
            Repositorio repoUsuario = new Repositorio(DAOUsuario);
            usuariosAPersistir.forEach(us->repoUsuario.agregar(us));



            //creo paises
            //String id, String name, String locale, String currency_id
           /* Pais pais1=new Pais();
            Pais pais2=new Pais();
            Pais pais3=new Pais();
            Pais pais4=new Pais();
            Pais pais5=new Pais();
            Pais pais6=new Pais();
            Pais pais7=new Pais();
            Pais pais8=new Pais();
            Pais pais9=new Pais();
            Pais pais10=new Pais();
            Pais pais11=new Pais();
            Pais pais12=new Pais();
            Pais pais13=new Pais();
            Pais pais14=new Pais();
            Pais pais15=new Pais();

            //creoProvincias
            //String id, String name
            Provincia provincia1= new Provincia();
            Provincia provincia2= new Provincia();
            Provincia provincia3= new Provincia();
            Provincia provincia4= new Provincia();
            Provincia provincia5= new Provincia();
            Provincia provincia6= new Provincia();
            Provincia provincia7= new Provincia();
            Provincia provincia8= new Provincia();
            Provincia provincia9= new Provincia();
            Provincia provincia10= new Provincia();
            Provincia provincia11= new Provincia();
            Provincia provincia12= new Provincia();
            Provincia provincia13= new Provincia();
            Provincia provincia14= new Provincia();
            Provincia provincia15= new Provincia();

            //creo ciudades
            //String id, String name
            Ciudad ciudad1=new Ciudad();
            Ciudad ciudad2=new Ciudad();
            Ciudad ciudad3=new Ciudad();
            Ciudad ciudad4=new Ciudad();
            Ciudad ciudad5=new Ciudad();
            Ciudad ciudad6=new Ciudad();
            Ciudad ciudad7=new Ciudad();
            Ciudad ciudad8=new Ciudad();
            Ciudad ciudad9=new Ciudad();
            Ciudad ciudad10=new Ciudad();
            Ciudad ciudad11=new Ciudad();
            Ciudad ciudad12=new Ciudad();
            Ciudad ciudad13=new Ciudad();
            Ciudad ciudad14=new Ciudad();
            Ciudad ciudad15=new Ciudad();


            //no se como hacerlo


            //creo direcciones es calle altura piso y depto
            Direccion direccion1=new Direccion("","","","");
            Direccion direccion2=new Direccion("","","","");
            Direccion direccion3=new Direccion("","","","");
            Direccion direccion4=new Direccion("","","","");
            Direccion direccion5=new Direccion("","","","");
            Direccion direccion6=new Direccion("","","","");
            Direccion direccion7=new Direccion("","","","");
            Direccion direccion8=new Direccion("","","","");
            Direccion direccion9=new Direccion("","","","");
            Direccion direccion10=new Direccion("","","","");
            Direccion direccion11=new Direccion("","","","");
            Direccion direccion12=new Direccion("","","","");
            Direccion direccion13=new Direccion("","","","");
            Direccion direccion14=new Direccion("","","","");
            Direccion direccion15=new Direccion("","","","");




            //creo direccionesPostales Direccion, cp, pais, provincia,ciudad
            DireccionPostal direccionPostal1=new DireccionPostal(direccion1,1,pais1,provincia1,ciudad1);
            DireccionPostal direccionPostal2=new DireccionPostal(direccion2,2,pais1,provincia2,ciudad2);
            DireccionPostal direccionPostal3=new DireccionPostal(direccion3,3,pais3,provincia3,ciudad3);
            DireccionPostal direccionPostal4=new DireccionPostal(direccion4,4,pais4,provincia4,ciudad4);
            DireccionPostal direccionPostal5=new DireccionPostal(direccion5,5,pais5,provincia5,ciudad5);
            DireccionPostal direccionPostal6=new DireccionPostal(direccion6,6,pais6,provincia6,ciudad6);
            DireccionPostal direccionPostal7=new DireccionPostal(direccion7,7,pais7,provincia7,ciudad7);
            DireccionPostal direccionPostal8=new DireccionPostal(direccion8,8,pais8,provincia8,ciudad8);
            DireccionPostal direccionPostal9=new DireccionPostal(direccion9,9,pais9,provincia9,ciudad9);
            DireccionPostal direccionPostal10=new DireccionPostal(direccion10,10,pais10,provincia10,ciudad10);
            DireccionPostal direccionPostal11=new DireccionPostal(direccion11,11,pais11,provincia11,ciudad11);
            DireccionPostal direccionPostal12=new DireccionPostal(direccion12,12,pais12,provincia12,ciudad12);
            DireccionPostal direccionPostal13=new DireccionPostal(direccion13,13,pais13,provincia13,ciudad13);
            DireccionPostal direccionPostal14=new DireccionPostal(direccion14,14,pais14,provincia14,ciudad14);
            DireccionPostal direccionPostal15=new DireccionPostal(direccion15,15,pais15,provincia15,ciudad15);

            //creo proveedores String nombre, String apellido, String dni, DireccionPostal unaDireccion
            Proveedor proveedor1=new Proveedor("","","",direccionPostal1);
            Proveedor proveedor2=new Proveedor("","","",direccionPostal2);
            Proveedor proveedor3=new Proveedor("","","",direccionPostal3);
            Proveedor proveedor4=new Proveedor("","","",direccionPostal4);
            Proveedor proveedor5=new Proveedor("","","",direccionPostal5);
            Proveedor proveedor6=new Proveedor("","","",direccionPostal6);
            Proveedor proveedor7=new Proveedor("","","",direccionPostal7);
            Proveedor proveedor8=new Proveedor("","","",direccionPostal8);
            Proveedor proveedor9=new Proveedor("","","",direccionPostal9);
            Proveedor proveedor10=new Proveedor("","","",direccionPostal10);
            Proveedor proveedor11=new Proveedor("","","",direccionPostal11);
            Proveedor proveedor12=new Proveedor("","","",direccionPostal12);
            Proveedor proveedor13=new Proveedor("","","",direccionPostal13);
            Proveedor proveedor14=new Proveedor("","","",direccionPostal14);
            Proveedor proveedor15=new Proveedor("","","",direccionPostal15);


            List<Proveedor> proveedoresAPersistir=new ArrayList<>();
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
            proveedoresAPersistir.add(proveedor13);
            proveedoresAPersistir.add(proveedor14);
            proveedoresAPersistir.add(proveedor15);




            //persistoProvedores
            DAO DAOProveedor = new DAOBBDD<Proveedor>();
            Repositorio repoProveedor = new Repositorio(DAOProveedor);
            proveedoresAPersistir.forEach(prov->repoProveedor.agregar(prov));

*/

            //genero y persisto actividades
            Sector agropecuario = new Sector(new ArrayList<>(),"AGROPECUARIO","Sector agropecuario");
            Categoria microArgo = new Categoria(agropecuario, TipoCategoria.MICRO,5,17260000f);
            Categoria pequeniaAgro = new Categoria(agropecuario, TipoCategoria.PEQUENIA,10,	71960000f);
            Categoria mediana1Agro = new Categoria(agropecuario, TipoCategoria.MEDIANA_TRAMO_1,50,426720000f);
            Categoria mediana2Agro = new Categoria(agropecuario, TipoCategoria.MEDIANA_TRAMO_2,215,676810000f);
            agropecuario.agregarCategoria(microArgo);
            agropecuario.agregarCategoria(pequeniaAgro);
            agropecuario.agregarCategoria(mediana1Agro);
            agropecuario.agregarCategoria(mediana2Agro);

            Sector iYm = new Sector(new ArrayList<>(),"INDUSTRIA Y MINERIA","Sector de industria y mineria");
            Categoria microiYm = new Categoria(iYm, TipoCategoria.MICRO,15,33920000f);
            Categoria pequeniaiYm = new Categoria(iYm, TipoCategoria.PEQUENIA,60,243290000f);
            Categoria mediana1iYm = new Categoria(iYm, TipoCategoria.MEDIANA_TRAMO_1,235,1651750000f);
            Categoria mediana2iYm = new Categoria(iYm, TipoCategoria.MEDIANA_TRAMO_2,655,2540380000f);
            iYm.agregarCategoria(microiYm);
            iYm.agregarCategoria(pequeniaiYm);
            iYm.agregarCategoria(mediana1iYm);
            iYm.agregarCategoria(mediana2iYm);


            Sector comercio = new Sector(new ArrayList<>(),"COMERCIO","Sector comercio");
            Categoria microcomercio = new Categoria(comercio, TipoCategoria.MICRO,7,36320000f);
            Categoria pequeniacomercio = new Categoria(comercio, TipoCategoria.PEQUENIA,35,	247200000f);
            Categoria mediana1comercio = new Categoria(comercio, TipoCategoria.MEDIANA_TRAMO_1,125,	1821760000f);
            Categoria mediana2comercio = new Categoria(comercio, TipoCategoria.MEDIANA_TRAMO_2,345,2602540000f);
            comercio.agregarCategoria(microcomercio);
            comercio.agregarCategoria(pequeniacomercio);
            comercio.agregarCategoria(mediana1comercio);
            comercio.agregarCategoria(mediana2comercio);

            Sector servicios = new Sector(new ArrayList<>(),"SERVICIOS","Sector de servicios");
            Categoria microservicios = new Categoria(servicios, TipoCategoria.MICRO,7,9900000f);
            Categoria pequeniaservicios = new Categoria(servicios, TipoCategoria.PEQUENIA,30,59710000f);
            Categoria mediana1servicios = new Categoria(servicios, TipoCategoria.MEDIANA_TRAMO_1,165,494200000f);
            Categoria mediana2servicios = new Categoria(servicios, TipoCategoria.MEDIANA_TRAMO_2,535,	705790000f);
            servicios.agregarCategoria(microservicios);
            servicios.agregarCategoria(pequeniaservicios);
            servicios.agregarCategoria(mediana1servicios);
            servicios.agregarCategoria(mediana2servicios);

            Sector construccion = new Sector(new ArrayList<>(),"CONSTRUCCION","Sector de construcciones");
            Categoria microconstruccion = new Categoria(construccion, TipoCategoria.MICRO,12,19450000f);
            Categoria pequeniaconstruccion = new Categoria(construccion, TipoCategoria.PEQUENIA,45,115370000f);
            Categoria mediana1construccion = new Categoria(construccion, TipoCategoria.MEDIANA_TRAMO_1,200,643710000f);
            Categoria mediana2construccion = new Categoria(construccion, TipoCategoria.MEDIANA_TRAMO_2,590,965460000f);
            construccion.agregarCategoria(microconstruccion);
            construccion.agregarCategoria(pequeniaconstruccion);
            construccion.agregarCategoria(mediana1construccion);
            construccion.agregarCategoria(mediana2construccion);






            Repositorio repoPaises= new Repositorio(new DAOBBDD<Pais>(Pais.class));
            List<Pais> todosLosPaises=repoPaises.getTodosLosElementos();
            Pais argentina=todosLosPaises.stream().filter(p->p.getName().equals("Argentina")).collect(Collectors.toList()).get(0);
            Pais eeuu=todosLosPaises.stream().filter(p->p.getName().equals("Venezuela")).collect(Collectors.toList()).get(0);
            Pais mexico=todosLosPaises.stream().filter(p->p.getName().equals("Mexico")).collect(Collectors.toList()).get(0);


            Repositorio repoProvincias= new Repositorio(new DAOBBDD<Provincia>(Provincia.class));
            List<Provincia> todosLasProvincias=repoProvincias.getTodosLosElementos();
            Provincia caba=todosLasProvincias.stream().filter(prov-> prov.getName().equals("Capital Federal")).collect(Collectors.toList()).get(0);
            Provincia provCiudadMexico=todosLasProvincias.stream().filter(prov-> prov.getName().equals("Distrito Federal")).collect(Collectors.toList()).get(0);
            Provincia nuevaYork=todosLasProvincias.stream().filter(prov-> prov.getName().equals("Nueva Esparta")).collect(Collectors.toList()).get(0);


            Repositorio repoCiudades= new Repositorio(new DAOBBDD<Ciudad>(Ciudad.class));
            List<Ciudad> todasLasCiudades=repoCiudades.getTodosLosElementos();
            Ciudad cabac=todasLasCiudades.stream().filter(ciu->ciu.getName().equals("Capital Federal")).collect(Collectors.toList()).get(0);
            Ciudad brooklyn=todasLasCiudades.stream().filter(ciu->ciu.getName().equals("Marcano")).collect(Collectors.toList()).get(0);
            Ciudad ciudadAUMexico=todasLasCiudades.stream().filter(ciu->ciu.getName().equals("Tláhuac")).collect(Collectors.toList()).get(0);

            Direccion direccionEntidad1=new Direccion("Av. Medrano","971","","");
            Direccion direccionEntidad2=new Direccion("Liberty Ave"," 720","","");
            Direccion direccionEntidad3=new Direccion("Roberto Gayol","55","","");
            Direccion direccionEntidad4=new Direccion("Jerónimo Salguero","2800","","");

            DireccionPostal direccionPostalEntidad1=new DireccionPostal(direccionEntidad1,1111,argentina,caba,cabac);
            DireccionPostal direccionPostalEntidad2=new DireccionPostal(direccionEntidad2,2222,eeuu,nuevaYork,brooklyn);
            DireccionPostal direccionPostalEntidad3=new DireccionPostal(direccionEntidad3,3333,mexico,provCiudadMexico,ciudadAUMexico);
            DireccionPostal direccionPostalEntidad4=new DireccionPostal(direccionEntidad4,4444,argentina,caba,cabac);



            //genero empresas
            Empresa tipoEntidad1= new Empresa();
            tipoEntidad1.setActividad(construccion);
            tipoEntidad1.setCantidadPersonal(150);
            tipoEntidad1.setPromedioVentasAnuales((float) 600000000.00);
            tipoEntidad1.setCuit("30-15269857-2");
            tipoEntidad1.setRazonSocial("EAAF BA");
            tipoEntidad1.setCodigoDeInscripcion("");
            tipoEntidad1.setDireccionPostal(direccionPostalEntidad1);
            tipoEntidad1.determinarCategoria();

            Empresa tipoEntidad2= new Empresa();
            tipoEntidad2.setActividad(construccion);
            tipoEntidad2.setCantidadPersonal(580);
            tipoEntidad2.setPromedioVentasAnuales((float) 960000000.00);
            tipoEntidad2.determinarCategoria();
            tipoEntidad2.setCuit("30-15789655-7");
            tipoEntidad2.setRazonSocial("EAAF NY");
            tipoEntidad2.setCodigoDeInscripcion("");
            tipoEntidad2.setDireccionPostal(direccionPostalEntidad2);

            Empresa tipoEntidad3= new Empresa();
            tipoEntidad3.setActividad(construccion);
            tipoEntidad3.setCantidadPersonal(240);
            tipoEntidad3.setPromedioVentasAnuales((float) 643710000.00);
            tipoEntidad3.determinarCategoria();
            tipoEntidad3.setCuit("30-77896583-9");
            tipoEntidad3.setRazonSocial("EAAF M");
            tipoEntidad3.setCodigoDeInscripcion("");
            tipoEntidad3.setDireccionPostal(direccionPostalEntidad3);

            Empresa tipoEntidad4= new Empresa();
            tipoEntidad4.setActividad(servicios);
            tipoEntidad4.setCantidadPersonal(8);
            tipoEntidad4.setPromedioVentasAnuales((float) 8000000.00);
            tipoEntidad4.determinarCategoria();
            tipoEntidad4.setCuit("30-25888897-8");
            tipoEntidad4.setRazonSocial("Surcos CS");
            tipoEntidad4.setCodigoDeInscripcion("");
            tipoEntidad4.setDireccionPostal(direccionPostalEntidad4);


            //Genero las entidades
            Entidad entidad1= new EntidadJuridica("Oficina central de Argentina","",tipoEntidad1);
            Entidad entidad2= new EntidadJuridica("Oficina central de Nueva York","",tipoEntidad2);
            Entidad entidad3= new EntidadJuridica("Oficina central de Mexico","",tipoEntidad3);
            EntidadJuridica entidad4= new EntidadJuridica("Surcos","",tipoEntidad4);
            Entidad entidad5= new EntidadBase("Andhes","", entidad4);



            //armo los ingresos de cada empresa
        Ingreso ingreso1=new Ingreso("Peso Argentino", 20000,LocalDate.parse("2020-02-25"), "Donación de terceros" ,new ArrayList());
        Ingreso ingreso2=new Ingreso("Peso Argentino", 10000, LocalDate.parse("2020-05-02"), "Donación de Rimoli SA" ,new ArrayList());
        Ingreso ingreso3=new Ingreso("Peso Argentino", 980000,LocalDate.parse("2020-08-03"), "Donación de Gran Imperio" ,new ArrayList());
        Ingreso ingreso4=new Ingreso("Peso Argentino", 10000, LocalDate.parse("2020-05-01"), "Donación Gabino SRL" ,new ArrayList());

        ingreso1.setEntidad(entidad1);
        ingreso2.setEntidad(entidad1);
        ingreso3.setEntidad(entidad1);
        ingreso4.setEntidad(entidad4);


        entidad1.agregarIngreso(ingreso1);
        entidad1.agregarIngreso(ingreso2);
        entidad1.agregarIngreso(ingreso3);
        entidad4.agregarIngreso(ingreso4);



        Repositorio repoEntidad= new Repositorio(new DAOBBDD<Entidad>(Entidad.class));
        repoEntidad.agregar(entidad1);
        repoEntidad.agregar(entidad2);
        repoEntidad.agregar(entidad3);
        repoEntidad.agregar(entidad4);
        repoEntidad.agregar(entidad5);

        Repositorio repoIngresos= new Repositorio(new DAOBBDD<Ingreso>(Ingreso.class));
        repoIngresos.agregar(ingreso1);
        repoIngresos.agregar(ingreso2);
        repoIngresos.agregar(ingreso3);
        repoIngresos.agregar(ingreso4);

































        //}
    }
}


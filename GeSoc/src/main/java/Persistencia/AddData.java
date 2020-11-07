package Persistencia;

import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Entidad.Direccion;
import Dominio.Entidad.DireccionPostal;
import Dominio.Rol.Administrador;
import Dominio.Rol.Estandar;
import Dominio.Rol.Revisor;
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
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//Aca metemos las cosas que queremos persistir desde un comienzo; algo con lo que poder trabajar apenas se levanta el sistema.
public class AddData {

    public static void main(String[] args) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM pers_usuarios u", Usuario.class);

        List<Usuario> results = query.getResultList();

        if (results.size() == 0) {
         //no hay nada cargado

            //creo roles
            Rol admin = new Administrador();
            Rol estandar=new Estandar();
            Rol revisor1= new Revisor();//hay que agregarle todas las operaciones de la org para que las revise
            Rol revisor2= new Revisor();//hay que agregarle todas las operaciones de la org para que las revise

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
            Pais pais1=new Pais();
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






































        }
    }
}


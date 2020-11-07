package Persistencia;

import Dominio.Contrasenia.Excepciones.ExcepcionCaracterEspecial;
import Dominio.Contrasenia.Excepciones.ExcepcionContraseniaComun;
import Dominio.Contrasenia.Excepciones.ExcepcionLongitud;
import Dominio.Contrasenia.Excepciones.ExcepcionNumero;
import Dominio.Rol.Administrador;
import Dominio.Rol.Rol;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOUsuario;
import Persistencia.Repos.Repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;


//Aca metemos las cosas que queremos persistir desde un comienzo; algo con lo que poder trabajar apenas se levanta el sistema.
public class AddData {

    public static void main(String[] args) throws IOException, ExcepcionNumero, ExcepcionLongitud, ExcepcionCaracterEspecial, ExcepcionContraseniaComun {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM pers_usuarios u", Usuario.class);

       // List<Usuario> results = query.getResultList();

       // if (results.size() == 0) {
            Rol admin = new Administrador();
            Usuario usuarioAdmin = new Usuario(admin, "martin", "urteaga", "M@rtin.98", "41589363","martin@hotmail.com");
            usuarioAdmin.setPersona();

            DAO DAOUsuario = new DAOUsuario();
            Repositorio repoUsuario = new Repositorio(DAOUsuario);
            repoUsuario.agregar(usuarioAdmin);
            
        //}
    }
}


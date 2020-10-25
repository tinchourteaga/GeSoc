package Persistencia;

import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAOBBDD;
import Persistencia.DAO.DAOUsuario;

public class RepositorioUsuario extends Repositorio {
    private static RepositorioUsuario instance;

    public static RepositorioUsuario getInstance() {
        if(instance == null){ instance = new RepositorioUsuario(DAOUsuario.getInstance()); }
        return instance;
    }

    private RepositorioUsuario(DAOBBDD dao){ this.setDao(dao); }

    public Usuario buscarPorPK(int usuario){ return (Usuario) this.dao.buscarPorPK(usuario); }

    public Usuario buscarPorNombre(String nombre){ return (Usuario) this.dao.buscarPorNombre(nombre); }

    public Usuario buscarPorUsuario(String usuario){ return (Usuario) this.dao.buscarPorUsuario(usuario); }
}

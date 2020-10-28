package Persistencia.DAO;

import Dominio.Usuario.Usuario;
import Persistencia.EntityManagerHelper;

import java.util.List;

public class DAOUsuario extends DAOBBDD implements DAO {
    private static DAOUsuario instance;

    public static DAOUsuario getInstance() {
        if(instance==null){ instance = new DAOUsuario(); }
        return instance;
    }

    @Override
    public Object buscarPorPK(int usuario) {
        return EntityManagerHelper.getEntityManager().find(Usuario.class, usuario);
    }

    @Override
    public Object buscarPorNombre(String nombre) {
        String query = "from Usuario where nombre = '" + nombre + "'";
        List usuarios =  EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
        EntityManagerHelper.closeEntityManager();
        if(usuarios!=null){ return usuarios.get(0); }
        return null;
    }

    @Override
    public Object buscarPorUsuario(String usuario) {
        String query = "from Usuario where persona = '" + usuario + "'";
        List usuarios =  EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
        EntityManagerHelper.closeEntityManager();
        if(usuarios!=null){ return usuarios.get(0); }
        return null;
    }
}
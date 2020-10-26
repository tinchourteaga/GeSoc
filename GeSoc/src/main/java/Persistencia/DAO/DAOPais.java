package Persistencia.DAO;

import Dominio.Usuario.Usuario;
import Persistencia.EntityManagerHelper;

import java.util.List;

public class DAOPais  extends DAOBBDD implements DAO {
    private static DAOPais instance;

    public static DAOPais getInstance() {
        if(instance==null){ instance = new DAOPais(); }
        return instance;
    }

    @Override
    public Object buscarPorPK(int pais) {
        return EntityManagerHelper.getEntityManager().find(Usuario.class, pais);
    }

    @Override
    public Object buscarPorNombre(String nombre) {
        String query = "from Usuario where name = '" + nombre + "'";
        List paises =  EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
        EntityManagerHelper.closeEntityManager();
        if(paises!=null){ return paises.get(0); }
        return null;
    }
}

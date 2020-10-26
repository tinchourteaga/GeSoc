package Persistencia;

import Persistencia.DAO.DAOBBDD;
import Persistencia.DAO.DAOProvincia;

public class RepositorioProvincia extends Repositorio {
    private static RepositorioProvincia instance;

    public static RepositorioProvincia getInstance() {
        if(instance == null){ instance = new RepositorioProvincia(DAOProvincia.getInstance()); }
        return instance;
    }

    public RepositorioProvincia(DAOBBDD dao){ this.setDao(dao); }
}

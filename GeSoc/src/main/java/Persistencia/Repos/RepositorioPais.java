package Persistencia.Repos;

import Lugares.Pais;
import Persistencia.DAO.DAOBBDD;
import Persistencia.DAO.DAOPais;

public class RepositorioPais extends Repositorio {
    private static RepositorioPais instance;

    public static RepositorioPais getInstance() {
        if(instance == null){ instance = new RepositorioPais(DAOPais.getInstance()); }
        return instance;
    }

    public RepositorioPais(DAOBBDD dao){ this.setDao(dao); }

    public Pais buscarPorPK(int pais){ return (Pais) this.dao.buscarPorPK(pais); }

    public Pais buscarPorNombre(String nombre){ return (Pais) this.dao.buscarPorNombre(nombre); }
}

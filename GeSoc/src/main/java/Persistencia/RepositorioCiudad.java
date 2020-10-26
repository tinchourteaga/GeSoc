package Persistencia;

import Persistencia.DAO.DAOBBDD;
import Persistencia.DAO.DAOCiudad;

public class RepositorioCiudad extends Repositorio {
    private static RepositorioCiudad instance;

    public static RepositorioCiudad getInstance() {
        if(instance == null){ instance = new RepositorioCiudad(DAOCiudad.getInstance()); }
        return instance;
    }

    public RepositorioCiudad(DAOBBDD dao){ this.setDao(dao); }
}

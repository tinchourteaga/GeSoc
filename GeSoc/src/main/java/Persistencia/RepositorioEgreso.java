package Persistencia;

import Dominio.Egreso.Core.Egreso;
import Persistencia.DAO.DAOBBDD;
import Persistencia.DAO.DAOEgreso;

public class RepositorioEgreso extends Repositorio {
    private static RepositorioEgreso instance;

    public static RepositorioEgreso getInstance() {
        if(instance == null){ instance = new RepositorioEgreso(DAOEgreso.getInstance()); }
        return instance;
    }

    private RepositorioEgreso(DAOBBDD dao){ this.setDao(dao); }

    public Egreso buscarPorPK(int egreso){ return (Egreso) this.dao.buscarPorPK(egreso); }
}

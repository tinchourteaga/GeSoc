package Persistencia.DAO;

import Dominio.Egreso.Core.Egreso;
import Persistencia.EntityManagerHelper;

public class DAOEgreso extends DAOBBDD implements DAO {
    private static DAOEgreso instance;

    public static DAOEgreso getInstance() {
        if (instance == null) {
            instance = new DAOEgreso();
        }
        return instance;
    }

    @Override
    public Object buscarPorPK(int egreso) {
        return EntityManagerHelper.getEntityManager().find(Egreso.class, egreso);
    }
}

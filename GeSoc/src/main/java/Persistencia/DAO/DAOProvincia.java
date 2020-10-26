package Persistencia.DAO;

public class DAOProvincia extends DAOBBDD implements DAO {
    private static DAOProvincia instance;

    public static DAOProvincia getInstance() {
        if(instance==null){ instance = new DAOProvincia(); }
        return instance;
    }
}

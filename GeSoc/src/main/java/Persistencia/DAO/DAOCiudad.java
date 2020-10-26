package Persistencia.DAO;

public class DAOCiudad extends DAOBBDD implements DAO {
    private static DAOCiudad instance;

    public static DAOCiudad getInstance() {
        if(instance==null){ instance = new DAOCiudad(); }
        return instance;
    }
}

package Persistencia.DAO;

import Persistencia.EntityManagerHelper;

import java.util.List;

public class DAOBBDD implements DAO {
    private String nombredb;
    private String conexionDB;
    private List<Object> listaElementos;

    public void DAOBBDDUsuario(String nombredb, String usuario, String passwod){
        //conexion a la DB
        //aca setearia la lista de usuarios
    }
    public <T> void agregar(T elemento){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.persist(elemento);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }
    public <T> void modificar(T elemento, T elementoModificado){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(elementoModificado);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }
    public <T> void eliminar(T elemento){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(elemento);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }
    public <T> boolean existe(T elemento) {
        return listaElementos.contains(elemento);
    }
    public <T> int buscar(T elemento) {
        return listaElementos.indexOf(elemento);
    }

    @Override
    public Object buscarPorId(String id) { return null; }

    @Override
    public Object buscarPorPK(int id) { return null; }

    @Override
    public Object buscarPorNombre(String nombre) {
        return null;
    }

    @Override
    public List<Object> getAllElementos() {
        return listaElementos;
    }

    @Override
    public List<Object> getAllElementosFrom(Class unaClase){
        return null;
    }
}

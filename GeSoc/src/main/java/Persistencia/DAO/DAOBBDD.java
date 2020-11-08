package Persistencia.DAO;

import Persistencia.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DAOBBDD <T> implements DAO {
    private String nombredb;
    private String conexionDB;
    private List<T> listaElementos;
    private Class<T> clazz;

    public DAOBBDD(Class<T> classEspecifica){
       clazz= classEspecifica;
    }
    public DAOBBDD(){   }

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
    public Object buscarPorPK(int id) {
        return null;
    }

    @Override
    public Object buscarPorNombre(String nombre) { return null; }

    @Override
    public Object buscarPorUsuario(String usuario) { return null; }

    @Override
    public List<T> getAllElementos() {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManager em=EntityManagerHelper.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(clazz);
        Root<T> c = q.from(clazz);
        q.select(c);
        TypedQuery<T> query = em.createQuery(q);
        List<T> resultados=query.getResultList();
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        return resultados;
    }

    @Override
    public List<Object> getAllElementosFrom(Class unaClase){
        return null;
    }
}

package Persistencia.DAO;

import Persistencia.EntityManagerHelper;

import java.util.List;

public interface DAO {

   default void agregar(Object objeto){
       EntityManagerHelper.getEntityManager().getTransaction().begin();
       EntityManagerHelper.persist(objeto);
       EntityManagerHelper.getEntityManager().getTransaction().commit();
   };

   default void modificar(Object objeto){
       EntityManagerHelper.getEntityManager().getTransaction().begin();
       EntityManagerHelper.getEntityManager().merge(objeto);
       EntityManagerHelper.getEntityManager().getTransaction().commit();
   };

   default void eliminar(Object objeto){
       EntityManagerHelper.getEntityManager().getTransaction().begin();
       EntityManagerHelper.getEntityManager().remove(objeto);
       EntityManagerHelper.getEntityManager().getTransaction().commit();
   };


    <T> boolean existe(T usuario);
    <T> int buscar(T usuario);
    <T> List<T> getAllElementos();
    <T> List<T> getAllElementosFrom(Class unaClase);
    Object buscarPorId(String id);
    Object buscarPorNombre(String nombre);
}

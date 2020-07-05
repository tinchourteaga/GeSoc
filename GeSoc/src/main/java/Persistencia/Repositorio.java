package Persistencia;

import Persistencia.DAO.DAO;

import java.util.List;

public class Repositorio {

    private static DAO dao;

    public static void setDao(DAO dao) {
        dao = dao;
    }

    public static <T> void agregar(T elemento){
        dao.agregar(elemento);
    }

    public  static <T> void modificar(T elemento, T elementoModificado){ dao.modificar(elemento, elementoModificado); }

    public static <T> void eliminar(T elemento){
        dao.eliminar(elemento);
    }

    public static <T> boolean existe(T elemento) {
        return dao.existe(elemento);
    }

    public static <T> int buscar(T elemento){
        return dao.buscar(elemento);
    }

    public static <T> List<T> getTodosLosElementos(){
        return dao.getAllElementos();
    }
}

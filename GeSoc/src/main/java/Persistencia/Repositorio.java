package Persistencia;

import Persistencia.DAO.DAO;

import java.util.List;

public class Repositorio {

    private  DAO dao;

    public  void setDao(DAO dao) {
        dao = dao;
    }

    public Repositorio(DAO dao){

        this.dao=dao;
}

    public  <T> void agregar(T elemento){
        dao.agregar(elemento);
    }

    public   <T> void modificar(T elemento, T elementoModificado){ dao.modificar(elemento, elementoModificado); }

    public  <T> void eliminar(T elemento){
        dao.eliminar(elemento);
    }

    public  <T> boolean existe(T elemento) {
        return dao.existe(elemento);
    }

    public  <T> Object buscar(T elemento){
        return dao.buscar(elemento);
    }

    public  <T> Object buscarPorId(String id){
        return dao.buscarPorId(id);
    }

    public  <T> Object buscarPorNombre(String nombre){
        return dao.buscarPorNombre(nombre);
    }

    public  <T> List<T> getTodosLosElementos(){
        return dao.getAllElementos();
    }
}

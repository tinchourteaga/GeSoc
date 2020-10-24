package Persistencia;

import Persistencia.DAO.DAO;

import java.util.List;

public class Repositorio {

    private  DAO dao;

    public Repositorio(DAO dao) {
        this.dao = dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public void agregar(Object objeto){ dao.agregar(objeto); }

    public void modificar(Object objeto,Object objeto2){ dao.modificar(objeto,objeto2); }

    public void eliminar(Object objeto){ dao.eliminar(objeto); }

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

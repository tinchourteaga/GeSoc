package Persistencia.DAO;

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
        listaElementos.add(elemento);
    }
    public <T> void modificar(T elemento, T elementoModificado){
        this.eliminar(elemento);
        this.agregar(elementoModificado);
    }
    public <T> void eliminar(T elemento){
        listaElementos.remove(elemento);
    }
    public <T> boolean existe(T elemento) {
        return listaElementos.contains(elemento);
    }
    public <T> int buscar(T elemento) {
        return listaElementos.indexOf(elemento);
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

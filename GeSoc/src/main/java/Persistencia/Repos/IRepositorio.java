package Persistencia.Repos;
public interface IRepositorio <T> {
    void agregar(T entity);
    void borrar(String id);
    void modificar(T entity);
    T obtener(String id);
    boolean existe(String id);
}

package Persistencia.DAO;

import java.util.List;

public interface DAO {
    <T> void agregar(T usuario);
    <T> void modificar(T usuario, T usuarioModificado);
    <T> void eliminar(T usuario);
    <T> boolean existe(T usuario);
    <T> int buscar(T usuario);
    <T> List<T> getAllElementos();
    <T> List<T> getAllElementosFrom(Class unaClase);
    Object buscarPorId(String id);
    Object buscarPorPK(int id);
    Object buscarPorNombre(String nombre);
}

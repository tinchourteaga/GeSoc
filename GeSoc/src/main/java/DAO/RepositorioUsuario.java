package DAO;

import Usuario.Usuario;

import java.util.List;

public class RepositorioUsuario {

    private static DAOUsuario dao;

    public static void setDao(DAOUsuario dao) {
        dao = dao;
    }

    public static void agregar(Usuario usuario){
        dao.agregar(usuario);
    }
    public  static void modificar(Usuario usuario, Usuario usuarioModificado){ dao.modificar(usuario, usuarioModificado); }
    public static void eliminar(Usuario usuario){
        dao.eliminar(usuario);
    }
    public static boolean existe(Usuario usuario) {
        return dao.existe(usuario);
    }
    public static int buscar(Usuario usuario){
        return dao.buscar(usuario);
    }
    public static List<Usuario> getTodosLosUsuario(){
        return dao.getAllUsuarios();
    }
}

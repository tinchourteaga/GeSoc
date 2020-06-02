package DAO;

import Usuario.Usuario;

import java.util.List;

public class DAOBBDDUsuario implements DAOUsuario {
    private String nombredb;
    private String conexionDB;
    private List<Usuario> usuarios;

    public void DAOBBDDUsuario(String nombredb, String usuario, String passwod){
        //conexion a la DB
        //aca setearia la lista de usuarios
    }
    public void agregar(Usuario usuario){
        usuarios.add(usuario);
    }
    public void modificar(Usuario usuario, Usuario nuevoUsuario){
        eliminar(usuario);
        agregar(nuevoUsuario);
    }
    public void eliminar(Usuario usuario){
        usuarios.remove(usuario);
    }
    public boolean existe(Usuario usuario) {
        return usuarios.contains(usuarios);
    }
    public int buscar(Usuario usuario) {
        return usuarios.indexOf(usuario);
    }
}

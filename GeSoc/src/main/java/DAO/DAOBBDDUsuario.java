package DAO;

import Usuario.Usuario;

public class DAOBBDDUsuario implements DAOUsuario {
    private String nombredb;
    private String conexionDB;

    public void DAOBBDDUsuario(String nombredb, String usuario, String passwod){
        //conexion a la DB
    }
    public void agregar(Usuario usuario){
    }
    public void modificar(Usuario usuario, Usuario nuevoUsuario){
    }
    public void eliminar(Usuario usuario){
    }
    public boolean existe(Usuario usuario) {
        return false;
    }
    public int buscar(Usuario usuario) {
        return 0;
    }
}

package DAO;

import Usuario.Usuario;

public class RepositorioUsuario {

    private DAOUsuario dao;

    public void setDao(DAOUsuario dao) {
        this.dao = dao;
    }

    public void agregar(Usuario usuario){
        this.dao.agregar(usuario);
    }
    public void modificar(Usuario usuario, Usuario nuevoUsuario){
        this.dao.modificar(usuario, nuevoUsuario);
    }
    public void eliminar(Usuario usuario){
        this.dao.eliminar(usuario);
    }
    public boolean existe(Usuario usuario) {
        return this.dao.existe(usuario);
    }
    public int buscar(Usuario usuario){
        return this.dao.buscar(usuario);
    }
}

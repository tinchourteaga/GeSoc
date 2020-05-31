package DAO;

public class RepositorioUsuario {

    private DAOUsuario dao

    public void setDao(DAOUsuario dao) {
        this.dao = dao;
    }

    public void agregar(Usuario usuario){
        this.dao.agregar(usuario);
    }
    public void modificar(Usuario usuario, Usuario nuevousuario){
        this.dao.modificar(usuario, nuevousuario);
    }
    public void eliminar(Usuario usuario){
        this.dao.eliminar(usuario);
    }
    public boolean existe(Usuario usuario) {
        return this.dao.existe(Usuario);
    }
    public Usuario buscar(Usuario usuario){
        return this.dao.buscar(usuario);
    }
}

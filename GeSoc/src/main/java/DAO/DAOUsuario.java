package DAO;

import Usuario.Usuario;

public interface DAOUsuario {
    public abstract void agregar(Usuario usuario);
    public abstract void modificar(Usuario usuario, Usuario nuevoUsuario);
    public abstract void eliminar(Usuario usuario);
    public abstract boolean existe(Usuario usuario);
    public abstract int buscar(Usuario usuario);
}

package DAO;

import Usuario.Usuario;
import java.util.ArrayList;
import java.util.List;


public class DAOMemoriaUsuario implements DAOUsuario {

    private List<Usuario> listaUsuarios;

    public DAOMemoriaUsuario(){
        listaUsuarios = new ArrayList<Usuario>();
    }

    public void agregar(Usuario usuario) {
        if (this.existe(usuario))
            listaUsuarios.add(usuario);
    }

    public void modificar(Usuario usuario, Usuario usuarioModificado) {
        int indiceUsuario;
        if (this.existe(usuario)){
            indiceUsuario=buscar(usuario);
            listaUsuarios.add(indiceUsuario, usuarioModificado);
        }
    }

    public void eliminar(Usuario usuario) {
        listaUsuarios.remove(usuario);
    }

    public boolean existe(Usuario usuario) {
        if (this.buscar(usuario) != -1){
            return true;
        }else{
            return false;
        }
    }

    public int buscar(Usuario usuario) {
        return listaUsuarios.indexOf(usuario.getNombre());
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return listaUsuarios;
    }
}

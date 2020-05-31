package DAO;

import Usuario.Usuario;
import java.util.ArrayList;
import java.util.List;


public class DAOMemoriaUsuario implements DAOUsuario {

    private List<Usuario> listausuarios;

    public DAOMemoriaUsuario(){
        listausuarios = new ArrayList<Usuario>();
    }

    public void agregar(Usuario usuario) {
        if (existe(usuario)) {
            listausuarios.add(usuario);
        }
    }

    public void modificar(Usuario usuario, Usuario nuevoUsuario) {
        int indiceUsuario;
        if (existe(usuario)){
            indiceUsuario=buscar(usuario);
            listausuarios.add(indiceUsuario, nuevoUsuario);
        }
    }

    public void eliminar(Usuario usuario) {
        listausuarios.remove(usuario);
    }

    public boolean existe(Usuario usuario) {
        if (buscar(usuario) != -1){
            return true;
        }else{
            return false;
        }
    }

    public int buscar(Usuario usuario) {
        return listausuarios.indexOf(usuario.getNombre());
    }
}

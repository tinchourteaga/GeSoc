package Servidor.Controllers;

import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAO;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Character.toLowerCase;

public class ControllerNombreUsuario {

    public static String otorgarNombreUsuario(Usuario unUsuario){
        DAO daoBBDD= new DAOBBDD<Usuario>(Usuario.class);
        Repositorio repoUsuario=new Repositorio<Usuario>(daoBBDD);
        List<Usuario> todosLosUsers=repoUsuario.getTodosLosElementos();
        List<String> todosLosUserNames= todosLosUsers.stream().map(user->user.getNickName()).collect(Collectors.toList());
        String userName=nombreOriginalUsuario(unUsuario,todosLosUserNames);
        return userName;

    }

    private static String nombreOriginalUsuario(Usuario unUsuario,List<String> todosLosUserNames) {
        String userName=toLowerCase(unUsuario.getNombre().charAt(0)) + unUsuario.getApellido().toLowerCase();
        int x=1;
        while(todosLosUserNames.contains(userName)){
            userName=userName+String.valueOf(x);
            x++;
        }
        return userName;
    }
}

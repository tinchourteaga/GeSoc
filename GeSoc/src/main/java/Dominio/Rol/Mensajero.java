package Dominio.Rol;

import Dominio.Egreso.Core.Egreso;
import Dominio.Usuario.Usuario;
import Persistencia.DAO.DAOBBDD;
import Persistencia.Repos.Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mensajero {

    public static List<Usuario> revisores = new ArrayList<Usuario>();

    public static void agregarRevisor(Usuario revisorCompra) {
        revisores.add(revisorCompra);
    }

    public static List<Usuario> obtenerRevisoresDe(Egreso unEgreso) {
        Repositorio repoUsuarios= new Repositorio(new DAOBBDD<Usuario>(Usuario.class));
        List<Usuario> todosLosUsers= repoUsuarios.getTodosLosElementos();
        return todosLosUsers.stream().filter(revisor -> (revisor.getEgresosAREvisar().stream().anyMatch(e->e.getEgreso()==unEgreso.getEgreso()))).collect(Collectors.toList());
    }

    public void removerRevisor(Usuario revisor){
        revisores.remove(revisor);
    }
}

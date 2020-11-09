package Dominio.Rol;

import Dominio.Egreso.Core.Egreso;
import Dominio.Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mensajero {

    public static List<Usuario> revisores = new ArrayList<Usuario>();

    public static void agregarRevisor(Usuario revisorCompra) {
        revisores.add(revisorCompra);
    }

    public static List<Usuario> obtenerRevisoresDe(Egreso unEgreso) { //si pincha putear a jorge
        return revisores.stream().filter(revisor -> ((Rol)revisor.getRol()).egresosARevisar.contains(unEgreso)).collect(Collectors.toList());
    }

    public void removerRevisor(Usuario revisor){
        revisores.remove(revisor);
    }
}

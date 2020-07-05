package Dominio.Rol;

import Dominio.Egreso.Core.Egreso;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mensajero {

    public static List<RolRevisorCompra> revisores = new ArrayList<RolRevisorCompra>();

    public static void agregarRevisor(RolRevisorCompra revisorCompra) {
        revisores.add(revisorCompra);
    }
    public static List<RolRevisorCompra> obtenerRevisoresDe(Egreso unEgreso) {
        return revisores.stream().filter(revisor -> revisor.getOperacionARevisar().equals(unEgreso)).collect(Collectors.toList());
    }
}

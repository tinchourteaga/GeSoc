package Egreso.Entidad;

import Egreso.Core.Egreso;

import java.util.List;

public class Entidad {
    String descripcion;
    String nombre;
    List<Egreso> operaciones;

    public Entidad(String nombreEntidad, String descripcionEntidad, List<Egreso> operacionesEntidad){
        this.descripcion=descripcionEntidad;
        this.nombre=nombreEntidad;
        this.operaciones=operacionesEntidad;

    }


    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }
    public List<Egreso> getOperaciones() {
        return operaciones;
    }
    public void agregarOperacion(Egreso unaOperacion){
        operaciones.add(unaOperacion);
    }

    public void removerOperacion(Egreso unaOperacion){
        operaciones.remove(unaOperacion);
    }
    public Egreso buscarOperacionPorIndice(int numero){
        return operaciones.get(numero);
    }
    public boolean tieneOperacion(Egreso operacion){
        return operaciones.contains(operacion);
    }
    public int indiceDeOperacion(Egreso operacion){
        return operaciones.indexOf(operacion);
    }
}

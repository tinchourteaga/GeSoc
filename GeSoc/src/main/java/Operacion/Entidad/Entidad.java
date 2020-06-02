package Operacion.Entidad;

import Operacion.Core.Operacion;

import java.util.List;

public class Entidad {
    String descripcion;
    String nombre;
    List<Operacion> operaciones;

    public Entidad(String nombreEntidad, String descripcionEntidad, List<Operacion> operacionesEntidad){
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
    public List<Operacion> getOperaciones() {
        return operaciones;
    }
    public void agregarOperacion(Operacion unaOperacion){
        operaciones.add(unaOperacion);
    }

    public void removerOperacion(Operacion unaOperacion){
        operaciones.remove(unaOperacion);
    }
    public Operacion buscarOperacionPorIndice(int numero){
        return operaciones.get(numero);
    }
    public boolean tieneOperacion(Operacion operacion){
        return operaciones.contains(operacion);
    }
    public int indiceDeOperacion(Operacion operacion){
        return operaciones.indexOf(operacion);
    }
}

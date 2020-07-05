package Egreso.Entidad;

import Egreso.Core.Egreso;
import Ingreso.Ingreso;
import java.util.ArrayList;
import java.util.List;

public abstract class Entidad {
    protected String descripcion;
    protected String nombre;
    protected List<Egreso> egresos;
    protected List<Ingreso> ingresos;

    public void agregarEgreso(Egreso unEgreso){
        egresos.add(unEgreso);
    }
    public void agregarIngreso(Ingreso unIngreso){
        ingresos.add(unIngreso);
    }
    public void removerEgreso(Egreso unEgreso){egresos.remove(unEgreso);}
    public void removerIngreso(Ingreso unIngreso){ingresos.remove(unIngreso);}
    public String getDescripcion() {
        return descripcion;
    }
    public String getNombre() {
        return nombre;
    }
    public List<Egreso> getOperaciones() {
        return egresos;
    }
    public Entidad(String nombreEntidad, String descripcionEntidad, List<Egreso> operacionesEntidad){
        this.descripcion=descripcionEntidad;
        this.nombre=nombreEntidad;
        this.egresos=operacionesEntidad;
        this.ingresos=new ArrayList<Ingreso>();
    }

}

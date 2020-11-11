package Dominio.Entidad;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import Dominio.Egreso.Core.Egreso;
import Dominio.Ingreso.Ingreso;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dom_entidades")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_entidad")
public abstract class Entidad {

    @Id
    @GeneratedValue
    private int entidad;

    @Column(name = "nombre")
    protected String nombre;

    @Column(name = "descripcion")
    protected String descripcion;

    @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL)
    protected List<Egreso> egresos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "entidad", referencedColumnName = "entidad")
    protected List<Ingreso> ingresos;

    @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL)
    protected List<Criterio> criterios;

    @OneToOne
    @JoinColumn(name = "entidad_juridica_asociada", referencedColumnName = "entidad")
    protected EntidadJuridica entidad_juridica_asociada;

    public Entidad() { }

    public Entidad(String nombreEntidad, String descripcionEntidad){
        this.descripcion=descripcionEntidad;
        this.nombre=nombreEntidad;
        this.egresos=new ArrayList<Egreso>();
        this.ingresos=new ArrayList<Ingreso>();
        this.criterios=new ArrayList<Criterio>();
    }

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
    public List<Criterio> getCriterios() { return criterios;}
    public void setEgresos(List<Egreso> egresos) { this.egresos = egresos; }
    public void setIngresos(List<Ingreso> ingresos) { this.ingresos = ingresos; }
    public void setCriterios(List<Criterio> criterios) { this.criterios = criterios; }
    public void setEntidad(int entidad) {this.entidad = entidad;}
    public int getEntidad() {return entidad;}
}

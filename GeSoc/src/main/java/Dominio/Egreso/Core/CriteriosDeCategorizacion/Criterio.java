package Dominio.Egreso.Core.CriteriosDeCategorizacion;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Entidad.Entidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "dom_criterios")
public class Criterio {

    @Id
    @GeneratedValue
    private int criterio;

    @OneToMany(mappedBy = "criterio", cascade = CascadeType.ALL)
    private List<CategoriaCriterio> categoriaCriterios;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "criterio_padre", referencedColumnName = "criterio")
    private Criterio criterio_padre;

    public Criterio getCriterio_padre() {
        return criterio_padre;
    }

    public void setCriterio_padre(Criterio criterio_padre) {
        this.criterio_padre = criterio_padre;
    }

    @OneToMany(mappedBy = "hijos", cascade = CascadeType.ALL)
    private List<Criterio> hijos;

    @Column(name = "nombre")
    private String nombreCriterio;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "entidad", referencedColumnName = "entidad")
    private Entidad entidad;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "presupuesto", referencedColumnName = "presupuesto")
    private Presupuesto presupuesto;

    public Criterio() { }

    public Criterio(List<CategoriaCriterio> categoriaCriterios, String nombreCriterio, String descripcion) {
        this.categoriaCriterios = categoriaCriterios;
        this.nombreCriterio = nombreCriterio;
        this.descripcion = descripcion;
        this.hijos = new ArrayList<Criterio>();
    }

    public List<CategoriaCriterio> getCategoriaCriterios() {
        return categoriaCriterios;
    }

    public void setCategoriaCriterios(List<CategoriaCriterio> categoriaCriterios) {
        this.categoriaCriterios = categoriaCriterios;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public List<Criterio> getHijos() {
        return hijos;
    }

    public String getNombreCriterio() {
        return nombreCriterio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void agregarCategoria(CategoriaCriterio unaCateogoria) {
        categoriaCriterios.add(unaCateogoria);
    }

    public CategoriaCriterio obtenerCategoria(String nombre) {
        return categoriaCriterios.stream().filter(categoriaCriterio -> categoriaCriterio.getNombreDeCategoria().equals(nombre)).collect(Collectors.toList()).get(0);
    }

    public void aplicar(Egreso egreso){
        hijos.forEach(hijo -> hijo.aplicar(egreso));
        egreso.getCriterioDeCategorizacion().add(this);
    }

    public int getCriterio() {
        return criterio;
    }

    public void setCriterio(int criterio) {
        this.criterio = criterio;
    }
}

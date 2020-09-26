package Dominio.Egreso.Core.CriteriosDeCategorizacion;

import Dominio.Egreso.Core.Egreso;

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

    @ManyToOne
    @JoinColumn(name = "criterio_padre", referencedColumnName = "criterio")
    private Criterio criterio_padre;
    
    @OneToMany(mappedBy = "hijos", cascade = CascadeType.ALL)
    private List<Criterio> hijos;

    @Column(name = "nombre")
    private String nombreCriterio;

    @Column(name = "descripcion")
    private String descripcion;

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
}

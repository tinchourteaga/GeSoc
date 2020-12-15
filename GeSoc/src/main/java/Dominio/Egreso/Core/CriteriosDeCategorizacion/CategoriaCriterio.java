package Dominio.Egreso.Core.CriteriosDeCategorizacion;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dom_categorias")
public class CategoriaCriterio {

    //El Usuario va a poder crear sus propias categorias
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoria;

    @Column(name = "nombre")
    private String nombreDeCategoria;

    @Column(name = "descripcion")
    private String descripicion;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "criterio", referencedColumnName = "criterio")
    private Criterio criterio;

    @ManyToMany(mappedBy = "categorias")
    private List<Egreso> egresos=new ArrayList();

    @ManyToMany(mappedBy = "categorias")
    private List<Presupuesto> presupuestos=new ArrayList();

    public List<Egreso> getEgresos() {
        return egresos;
    }

    public void setEgresos(List<Egreso> egresos) {
        this.egresos = egresos;
    }

    public Criterio getCriterio() {
        return criterio;
    }
    public void setCriterio(Criterio unCriterio) {
        this.criterio=unCriterio;
    }
    public CategoriaCriterio() { }

    public CategoriaCriterio(String desc, String nombreDeCategoria) {
        this.descripicion=desc;
        this.nombreDeCategoria=nombreDeCategoria;
    }
    public String getDescripicion() {
        return descripicion;
    }
    public String getNombreDeCategoria() {
        return nombreDeCategoria;
    }
    public void cambiarDescripicion(String Nuevadescripicion) {
        this.descripicion = descripicion;
    }
    public void CambiarNombreCategoria(String nuevoNombreDeCategoria) {
        this.nombreDeCategoria = nombreDeCategoria;
    }
    public int getCategoria() { return categoria; }
    public void setCategoria(int categoria) { this.categoria = categoria; }
}

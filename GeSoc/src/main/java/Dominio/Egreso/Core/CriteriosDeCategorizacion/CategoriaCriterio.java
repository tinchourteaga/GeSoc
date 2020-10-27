package Dominio.Egreso.Core.CriteriosDeCategorizacion;

import javax.persistence.*;

@Entity
@Table(name = "dom_categorias")
public class CategoriaCriterio {

    //El Usuario va a poder crear sus propias categorias
    @Id
    @GeneratedValue
    private int categoria;

    @Column(name = "nombre")
    private String nombreDeCategoria;

    @Column(name = "descripcion")
    private String descripicion;

    @ManyToOne
    @JoinColumn(name = "criterio", referencedColumnName = "criterio")
    private Criterio criterio;

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

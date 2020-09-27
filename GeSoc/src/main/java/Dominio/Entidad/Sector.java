package Dominio.Entidad;

import Dominio.Entidad.Categorias.Categoria;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ent_sector")
public class Sector {
    @Id
    @GeneratedValue
    private int sector;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Categoria> categorias;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public Sector(List<Categoria> categorias, String nombre, String descripcion) {
        this.categorias = categorias;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void agregarCategoria(Categoria categoria){
        this.categorias.add(categoria);
    }

    public void removerCategoria(Categoria categoria){
        this.categorias.add(categoria);
    }
}

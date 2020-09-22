package Dominio.Entidad;

import Dominio.Entidad.Categorias.Categoria;

import java.util.List;

public class Sector {

    private List<Categoria> categorias;
    private String nombre;
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

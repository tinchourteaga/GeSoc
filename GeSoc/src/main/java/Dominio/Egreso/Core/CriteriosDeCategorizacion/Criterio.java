package Dominio.Egreso.Core.CriteriosDeCategorizacion;

import java.util.List;
import java.util.stream.Collectors;

public class Criterio {
    private List<Categoria> categorias;
    private String nombreCriterio;
    private String descripcion;

    public Criterio(List<Categoria> categorias, String nombreCriterio, String descripcion) {
        this.categorias = categorias;
        this.nombreCriterio = nombreCriterio;
        this.descripcion = descripcion;
    }

    public String getNombreCriterio() {
        return nombreCriterio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }
    public void agregarCategoria(Categoria unaCateogoria) {
        categorias.add(unaCateogoria);
    }
    public Categoria obtenerCategoria(String nombre) {
        return categorias.stream().filter(categoria->categoria.getNombreDeCategoria().equals(nombre)).collect(Collectors.toList()).get(0);
    }
}

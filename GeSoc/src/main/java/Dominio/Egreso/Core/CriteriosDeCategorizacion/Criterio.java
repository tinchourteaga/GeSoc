package Dominio.Egreso.Core.CriteriosDeCategorizacion;

import Dominio.Egreso.Core.Egreso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Criterio {
    private List<Categoria> categorias;
    private List<Criterio> hijos;
    private String nombreCriterio;
    private String descripcion;

    public Criterio(List<Categoria> categorias, String nombreCriterio, String descripcion) {
        this.categorias = categorias;
        this.nombreCriterio = nombreCriterio;
        this.descripcion = descripcion;
        this.hijos = new ArrayList<Criterio>();
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
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

    public void agregarCategoria(Categoria unaCateogoria) {
        categorias.add(unaCateogoria);
    }

    public Categoria obtenerCategoria(String nombre) {
        return categorias.stream().filter(categoria->categoria.getNombreDeCategoria().equals(nombre)).collect(Collectors.toList()).get(0);
    }

    public void aplicar(Egreso egreso){
        hijos.forEach(hijo -> hijo.aplicar(egreso));
        egreso.getCriterioDeCategorizacion().add(this);
    }
}

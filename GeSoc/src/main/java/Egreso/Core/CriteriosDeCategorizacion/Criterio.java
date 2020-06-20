package Egreso.Core.CriteriosDeCategorizacion;

import Egreso.Entidad.Entidad;

import java.util.HashMap;

public abstract class Criterio {
    HashMap<String,Categoria> categorias;

    public abstract void aplicar(Entidad unaEntidad);

    public HashMap<String, Categoria> getCategorias() {
        return categorias;
    }
    public void agregarCategoria(Categoria unaCateogoria,String nombre) {
        categorias.put(nombre,unaCateogoria);
    }
    public Categoria obtenerCategoria(String nombre) {
        return categorias.get(nombre);
    }
}

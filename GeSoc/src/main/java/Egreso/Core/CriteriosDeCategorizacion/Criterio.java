package Egreso.Core.CriteriosDeCategorizacion;

import Egreso.Core.Egreso;
import Egreso.Entidad.Entidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Criterio {
    List<Categoria> categorias=new ArrayList<>();

    public abstract void aplicar(Egreso unaEgreso);//en este metodo voy a hacer algo como
    /*egreso.categorias.add(una categoria que elijo segun el criterio)*/

    public List<Categoria> getCategorias() {
        return categorias;
    }
    public void agregarCategoria(Categoria unaCateogoria) {
        categorias.add(unaCateogoria);
    }
    public Categoria obtenerCategoria(String nombre) {
        return categorias.stream().filter(categoria->categoria.nombreDeCategoria.equals(nombre)).collect(Collectors.toList()).get(0);
    }
}

package Operacion.Entidad.Categorias;

import Operacion.Entidad.Sector;

public class Categoria {
    Sector descripcion;
    TipoCategoria nombre; //correccion falta cambiar el constructor para setearlo bien
    Integer personalMaximo;
    Float ventasAnuales;

    public Categoria(Sector descripcion, TipoCategoria nombre, Integer personalMaximo,Float ventasAnuales){
        this.descripcion=descripcion;
        this.nombre=nombre;
        this.personalMaximo=personalMaximo;
        this.ventasAnuales=ventasAnuales;
    }

    public Sector getDescripcion() {
        return descripcion;
    }

    public TipoCategoria getNombre() {
        return nombre;
    }

    public Integer getPersonalMaximo() {
        return personalMaximo;
    }

    public Float getVentasAnuales() {
        return ventasAnuales;
    }
}

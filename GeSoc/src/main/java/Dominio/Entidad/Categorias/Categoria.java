package Dominio.Entidad.Categorias;

import Dominio.Entidad.Sector;

public class Categoria{
    private Sector descripcion;
    private TipoCategoria nombre;
    private Integer personalMaximo;
    private Float ventasAnuales;

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

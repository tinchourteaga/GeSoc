package Dominio.Entidad.Categorias;

public class Categoria{

    private TipoCategoria nombre;
    private Integer personalMaximo;
    private Float ventasAnuales;

    public Categoria(TipoCategoria nombre, Integer personalMaximo,Float ventasAnuales){
        this.nombre=nombre;
        this.personalMaximo=personalMaximo;
        this.ventasAnuales=ventasAnuales;
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

package Dominio.Entidad.Categorias;

import Dominio.Entidad.Sector;
import Dominio.Entidad.TipoEntidadJuridica;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ent_categorias")
public class Categoria{

    @Id
    @GeneratedValue
    private int categoria;

    @ManyToOne
    @JoinColumn(name = "sector", referencedColumnName = "sector")
    private Sector sector;

    @Column(name = "nombre")
    @Enumerated(value = EnumType.STRING)
    private TipoCategoria nombre;

    @Column(name = "personal_maximo")
    private Integer personalMaximo;

    @Column(name = "ventas_anuales")
    private double ventasAnuales;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL)
    private List<TipoEntidadJuridica> entidades_juridicas;

    public Categoria() { }

    public Categoria(Sector sector, TipoCategoria nombre, Integer personalMaximo,Float ventasAnuales){
        this.sector=sector;
        this.nombre=nombre;
        this.personalMaximo=personalMaximo;
        this.ventasAnuales=ventasAnuales;
    }

    public Sector getSector() { return sector; }
    public TipoCategoria getNombre() {
        return nombre;
    }
    public Integer getPersonalMaximo() {
        return personalMaximo;
    }
    public double getVentasAnuales() {
        return ventasAnuales;
    }
    public int getCategoria() { return categoria;}
    public void setCategoria(int categoria) {this.categoria = categoria;}

}

package Dominio.Entidad;

import javax.persistence.*;

@Entity
@Table(name = "dom_entidades_juridicas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoEntidad",
        discriminatorType = DiscriminatorType.STRING)
public abstract class TipoEntidadJuridica {
    @Id
    @GeneratedValue
    private int entidad_juridica;

    @Column(name = "razon_social")
    protected String razonSocial;

    @Column(name = "cuit_cuil")
    protected String cuit;

    @Embedded
    protected DireccionPostal direccionPostal;

    @Column(name = "cod_igj")
    protected String codigoDeInscripcion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actividad", referencedColumnName = "sector")
    protected Sector actividad;

    /*@ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "categoria")
    protected CategoriaCriterio categoria;*/

    @Column(name = "cant_personal")
    protected Integer cantidadPersonal;

    @Column(name = "prom_ventas_anuales")
    protected Float promedioVentasAnuales;

    public TipoEntidadJuridica() { }

    //GETTERS Y SETTERS
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getCuit() {
        return cuit;
    }
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    public DireccionPostal getDireccionPostal() {
        return direccionPostal;
    }
    public void setDireccionPostal(DireccionPostal direccionPostal) {
        this.direccionPostal = direccionPostal;
    }
    public String getCodigoDeInscripcion() {
        return codigoDeInscripcion;
    }
    public void setCodigoDeInscripcion(String codigoDeInscripcion) {
        this.codigoDeInscripcion = codigoDeInscripcion;
    }
    public Sector getActividad() {
        return actividad;
    }
    public void setActividad(Sector actividad) {
        this.actividad = actividad;
    }
    public Integer getCantidadPersonal() {
        return cantidadPersonal;
    }
    public void setCantidadPersonal(Integer cantidadPersonal) {
        this.cantidadPersonal = cantidadPersonal;
    }
    public Float getPromedioVentasAnuales() {
        return promedioVentasAnuales;
    }
    public void setPromedioVentasAnuales(Float promedioVentasAnuales) {this.promedioVentasAnuales = promedioVentasAnuales; }
}

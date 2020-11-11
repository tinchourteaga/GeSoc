package Dominio.Ingreso;

import Converters.LocalDateAttributeConverter;
import Dominio.Egreso.Core.CriteriosDeCategorizacion.CategoriaCriterio;
import Dominio.Egreso.Core.Egreso;
import Dominio.Entidad.Entidad;
import Dominio.Ingreso.Excepciones.NoPuedoAsignarMasDineroDelQueTengoException;
import Dominio.Moneda.Valor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dom_ingresos")
public class Ingreso {
    @Id
    @GeneratedValue
    private int ingreso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "valor")
    private Valor valor;

    @Column(name = "fecha")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "entidad", referencedColumnName = "entidad")
    private Entidad entidad;

    @OneToMany(mappedBy = "ingreso", cascade = CascadeType.ALL)
    private List<Egreso> gastadoEn;

    @Transient
    private List<CategoriaCriterio> categoriasAsociadas = new ArrayList<>();

    public Ingreso() { }

    public Ingreso(String moneda, double importe, LocalDate fecha,String descripcion,List<Egreso>egresos){
       this.valor= new Valor(moneda,importe);
       this.fecha=fecha;
       this.descripcion=descripcion;
       this.gastadoEn=egresos;
   }

   //GETTERS
    public Valor getValor() {
        return valor;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public List<Egreso> getGastadoEn() {
        return gastadoEn;
    }
    public void agregarEgreso(Egreso unEgreso) throws NoPuedoAsignarMasDineroDelQueTengoException {
       double gastoTotal=gastadoEn.stream().mapToDouble(egreso->egreso.getValor().getImporte()).sum()+unEgreso.getValor().getImporte();
       if(gastoTotal<this.valor.getImporte()){
           gastadoEn.add(unEgreso);
       }else{
           throw new NoPuedoAsignarMasDineroDelQueTengoException();
       }

    }
    public void setIngreso(int ingreso) {
        this.ingreso = ingreso;
    }
    public int getIngreso() {
        return ingreso;
    }
    public void agregarCategoria(CategoriaCriterio unaCategoria){
       this.categoriasAsociadas.add(unaCategoria);
    }
    public void quitarCategoria(CategoriaCriterio unaCategoria){
        this.categoriasAsociadas.remove(unaCategoria);
    }
    public Entidad getEntidad() { return entidad; }

    public void setEntidad(Entidad entidad) {this.entidad = entidad; }

    public List<CategoriaCriterio> getCategoriasAsociadas() {
        return categoriasAsociadas;
    }
}

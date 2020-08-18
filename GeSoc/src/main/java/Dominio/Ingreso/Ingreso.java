package Dominio.Ingreso;

import Dominio.Egreso.Core.Egreso;
import Dominio.Ingreso.Excepciones.NoPuedoAsignarMasDineroDelQueTengoException;
import Dominio.Moneda.Valor;
import java.util.Date;
import java.util.List;

public class Ingreso {

    private Valor valor;
    private Date fecha;
    private String descripcion;
    private List<Egreso> gastadoEn;

   public Ingreso(String pais, double importe, Date fecha,String descripcion,List<Egreso>egresos){
       this.valor= new Valor(pais,importe);
       this.fecha=fecha;
       this.descripcion=descripcion;
       this.gastadoEn=egresos;
   }

   //GETTERS
    public Valor getValor() {
        return valor;
    }
    public Date getFecha() {
        return fecha;
    }
    public String getDescripcion() {
        return descripcion;
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
}

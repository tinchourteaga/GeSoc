package Dominio.Ingreso;

import Dominio.Egreso.Core.Egreso;
import Dominio.Ingreso.Excepciones.NoPuedoAsignarMasDineroDelQueTengoException;
import java.util.Date;
import java.util.List;

public class Ingreso {

    private double valor;
    private Date fecha;
    private String descripcion;
    private List<Egreso> gastadoEn;

   public Ingreso(double valor,Date fecha,String descripcion,List<Egreso>egresos){
       this.valor=valor;
       this.fecha=fecha;
       this.descripcion=descripcion;
       this.gastadoEn=egresos;
   }

   //GETTERS
    public double getValor() {
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
       double gastoTotal=gastadoEn.stream().mapToDouble(egreso->egreso.getValor().doubleValue()).sum()+unEgreso.getValor().doubleValue();
       if(gastoTotal<this.valor){
           gastadoEn.add(unEgreso);
       }else{
           throw new NoPuedoAsignarMasDineroDelQueTengoException();
       }

    }
}

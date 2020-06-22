package Ingreso;

import Egreso.Core.DocumentoComercial;
import Egreso.Core.MetodoDePago;
import Egreso.Core.Egreso;

import java.util.Date;

public class Ingreso {

    double valor;
    Date fecha;
    String descripcion;
   // Egreso operacionAsociada= null;
    //De aca para abajo no sabemos si esta bien
    //al final no estaba bien y lo deberiamos dejar sin esas cosas
    //por eso las comento
   /* MetodoPago medioPago;
    DocumentoComercial documentoComercial;
    TipoDeIngreso tipoDeIngreso;*/
   public Ingreso(double valor,Date fecha,String descripcion){
       this.valor=valor;
       this.fecha=fecha;
       this.descripcion=descripcion;
   }

    public double getValor() {
        return valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

package Ingreso;

import Egreso.Core.DocumentoComercial;
import Egreso.Core.MetodoPago;
import Egreso.Core.Egreso;

import java.util.Date;

public class Ingreso {

    double valor;
    Date fecha;
    String descripcion;
    Egreso operacionAsociada= null;
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
   public Ingreso(double valor,Date fecha,String descripcion,Egreso unaCompra){
       this.valor=valor;
       this.fecha=fecha;
       this.descripcion=descripcion;
       this.operacionAsociada=unaCompra;
       //no se si hacerlo con un optional y al joraca pero despues voy a complicarme la vida con eso
   }
}

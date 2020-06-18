package Ingreso;

import Operacion.Core.DocumentoComercial;
import Operacion.Core.MetodoPago;
import Operacion.Core.Operacion;
import Operacion.Core.TipoDeMedioDePago;

import java.util.Date;

public class Ingreso {

    double valor;
    Date fecha;
    String descripcion;
    Operacion operacionAsociada;
    //De aca para abajo no sabemos si esta bien
    MetodoPago medioPago;
    DocumentoComercial documentoComercial;
    TipoDeIngreso tipoDeIngreso;
}

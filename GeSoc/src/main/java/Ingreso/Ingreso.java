package Ingreso;

import Egreso.Core.DocumentoComercial;
import Egreso.Core.MetodoPago;
import Egreso.Core.Egreso;

import java.util.Date;

public class Ingreso {

    double valor;
    Date fecha;
    String descripcion;
    Egreso operacionAsociada;
    //De aca para abajo no sabemos si esta bien
    MetodoPago medioPago;
    DocumentoComercial documentoComercial;
    TipoDeIngreso tipoDeIngreso;
}

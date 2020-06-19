package Egreso.Core;

import java.util.List;

public class Presupuesto {
    Categoria unaCategoria;
    float valor;
    List<Detalle> detalles;
    DocumentoComercial documentoComercial;

    public float getValor() {
        return valor;
    }
}

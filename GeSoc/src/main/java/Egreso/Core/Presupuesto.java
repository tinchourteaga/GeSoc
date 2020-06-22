package Egreso.Core;

import Egreso.Core.CriteriosDeCategorizacion.Categoria;

import java.util.List;

public class Presupuesto {
    List<Categoria> categorias;
    float valor;
    List<Detalle> detalles;
    DocumentoComercial documentoComercial;


    public List<Categoria> getCategorias() {
        return categorias;
    }

    public float getValor() {
        return valor;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public DocumentoComercial getDocumentoComercial() {
        return documentoComercial;
    }
}

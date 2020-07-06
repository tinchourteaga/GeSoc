package Dominio.Egreso.Core;

import Dominio.Egreso.Core.CriteriosDeCategorizacion.Criterio;
import java.util.Date;
import java.util.List;

public class Presupuesto {

    private List<Criterio> criterios;
    private float valor;
    private List<Detalle> detalles;
    private DocumentoComercial documentoComercial;
    private Date fecha;

    public Presupuesto(List<Criterio> criterios, float valor, List<Detalle> detalles, DocumentoComercial documentoComercial) {
        this.criterios = criterios;
        this.valor = valor;
        this.detalles = detalles;
        this.documentoComercial = documentoComercial;
        this.fecha = new Date();
    }

    public List<Criterio> getCriterios() {
        return criterios;
    }
    public float getValor() { return valor;}
    public List<Detalle> getDetalles() {return detalles;}
    public DocumentoComercial getDocumentoComercial() { return documentoComercial;}
    public Date getFecha() {return fecha; }
}

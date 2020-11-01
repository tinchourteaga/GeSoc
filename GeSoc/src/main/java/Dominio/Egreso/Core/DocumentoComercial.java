package Dominio.Egreso.Core;

import javax.persistence.*;

@Entity
@Table(name = "dom_documentos_comerciales")
public class DocumentoComercial {
    @Id
    @GeneratedValue
    private int documento_comercial;

    @Column(name = "nombre")
    @Enumerated(value = EnumType.STRING)
    private TipoDocumentoComercial tipo;

    @Column(name = "descripcion")
    private String detalle;

    @Column(name = "ruta_archivo")
    private String rutaDeArchivo;

    public DocumentoComercial() {}

    //No hago setters porque si le cambio al documento el tipo rompo muchas cosas :(
    public DocumentoComercial(TipoDocumentoComercial documento,String dato) {
        this.tipo = documento;
        this.detalle = dato;
    }

    public String getDetalle() {
        return detalle;
    }
    public TipoDocumentoComercial getTipo() {
        return tipo;
    }
    public String getRutaDeArchivo() { return rutaDeArchivo; }
    public void setRutaDeArchivo(String rutaDeArchivo) { this.rutaDeArchivo = rutaDeArchivo;}
}

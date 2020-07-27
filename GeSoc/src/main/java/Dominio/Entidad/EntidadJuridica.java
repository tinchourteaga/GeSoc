package Dominio.Entidad;

public class EntidadJuridica extends Entidad{

    private TipoEntidadJuridica tipoEntidadJuridica;

    public EntidadJuridica(String nombreEntidad, String descripcionEntidad, TipoEntidadJuridica tipo) {
        super(nombreEntidad, descripcionEntidad);
        this.tipoEntidadJuridica = tipo;
    }

    public TipoEntidadJuridica getTipoEntidadJuridica() {
        return tipoEntidadJuridica;
    }
}

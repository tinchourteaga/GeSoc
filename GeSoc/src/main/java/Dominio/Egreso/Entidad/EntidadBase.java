package Dominio.Egreso.Entidad;

public class EntidadBase extends Entidad{

    EntidadJuridica entidadJuridicaAsociada;

    public EntidadBase(String nombreEntidad, String descripcionEntidad,EntidadJuridica entidadAsociada) {
        super(nombreEntidad, descripcionEntidad);
        this.entidadJuridicaAsociada=entidadAsociada;
    }
}

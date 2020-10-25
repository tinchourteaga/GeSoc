package Dominio.Entidad;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "J")
@Embeddable
public class EntidadJuridica extends Entidad{
    @Transient
    private TipoEntidadJuridica tipoEntidadJuridica;

    public EntidadJuridica(String nombreEntidad, String descripcionEntidad, TipoEntidadJuridica tipo) {
        super(nombreEntidad, descripcionEntidad);
        this.tipoEntidadJuridica = tipo;
    }

    public TipoEntidadJuridica getTipoEntidadJuridica() {
        return tipoEntidadJuridica;
    }
}

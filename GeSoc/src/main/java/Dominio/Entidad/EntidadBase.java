package Dominio.Entidad;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "B")
public class EntidadBase extends Entidad{
    @Embedded
    EntidadJuridica entidadJuridicaAsociada;

    public EntidadBase(String nombreEntidad, String descripcionEntidad,EntidadJuridica entidadAsociada) {
        super(nombreEntidad, descripcionEntidad);
        this.entidadJuridicaAsociada=entidadAsociada;
    }
}

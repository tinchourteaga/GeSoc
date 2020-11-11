package Dominio.Entidad;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "B")
public class EntidadBase extends Entidad{
    @ManyToOne
    @JoinColumn(name="entidad_juridica_asociada",insertable = false, updatable = false)
    EntidadJuridica entidadJuridicaAsociada;

    public EntidadBase() { }

    public EntidadBase(String nombreEntidad, String descripcionEntidad,EntidadJuridica entidadAsociada) {
        super(nombreEntidad, descripcionEntidad);
        this.entidadJuridicaAsociada=entidadAsociada;
        entidadAsociada.entidades.add(this);
    }
}

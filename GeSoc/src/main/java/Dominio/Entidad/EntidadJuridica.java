package Dominio.Entidad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "Jurídica")
@Embeddable
public class EntidadJuridica extends Entidad{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entidad_juridica", referencedColumnName = "entidad_juridica")
    private TipoEntidadJuridica tipoEntidadJuridica;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="entidad_juridica_asociada",insertable = true, updatable = true)
    List<EntidadBase> entidades=new ArrayList();
    public EntidadJuridica() { }

    public EntidadJuridica(String nombreEntidad, String descripcionEntidad, TipoEntidadJuridica tipo) {
        super(nombreEntidad, descripcionEntidad);
        this.tipoEntidadJuridica = tipo;
    }

    public TipoEntidadJuridica getTipoEntidadJuridica() {
        return tipoEntidadJuridica;
    }
}

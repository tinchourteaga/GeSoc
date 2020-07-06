package Dominio.Egreso.Entidad;

import Dominio.Egreso.Core.Egreso;
import java.util.List;

public class EntidadBase extends Entidad{
    EntidadJuridica entidadJuridicaAsociada;

    public EntidadBase(String nombreEntidad, String descripcionEntidad,EntidadJuridica entidadAsociada) {
        super(nombreEntidad, descripcionEntidad);
        this.entidadJuridicaAsociada=entidadAsociada;
    }
}

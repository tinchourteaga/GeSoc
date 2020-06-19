package Egreso.Entidad;

import Egreso.Core.Egreso;

import java.util.List;

public class EntidadBase extends Entidad{
    EntidadJuridica entidadJuridicaAsociada;

    public EntidadBase(String nombreEntidad, String descripcionEntidad, List<Egreso> operacionesEntidad,EntidadJuridica entidadAsociada) {
        super(nombreEntidad, descripcionEntidad, operacionesEntidad);
        this.entidadJuridicaAsociada=entidadAsociada;
    }
}

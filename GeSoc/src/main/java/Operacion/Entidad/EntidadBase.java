package Operacion.Entidad;

import Operacion.Operacion;

import java.util.List;

public class EntidadBase extends Entidad{
    EntidadJuridica entidadJuridicaAsociada;

    public EntidadBase(String nombreEntidad, String descripcionEntidad, List<Operacion> operacionesEntidad,EntidadJuridica entidadAsociada) {
        super(nombreEntidad, descripcionEntidad, operacionesEntidad);
        this.entidadJuridicaAsociada=entidadAsociada;
    }
}

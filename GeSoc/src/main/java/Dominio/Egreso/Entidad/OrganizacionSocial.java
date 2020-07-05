package Dominio.Egreso.Entidad;

import Dominio.Egreso.Core.Egreso;
import java.util.List;

public class OrganizacionSocial extends EntidadJuridica{
    public OrganizacionSocial(String nombreEntidad, String descripcionEntidad, List<Egreso> operacionesEntidad, String rs, String cuitEntidad, String cp, String ci, String tipo, Sector actividad, Integer personal, Float promedio) {
        super(nombreEntidad, descripcionEntidad, operacionesEntidad, rs, cuitEntidad, cp, ci, tipo, actividad, personal, promedio);
    }
}

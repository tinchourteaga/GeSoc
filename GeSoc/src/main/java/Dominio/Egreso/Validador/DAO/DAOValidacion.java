package Dominio.Egreso.Validador.DAO;

import Dominio.Egreso.Validador.Validaciones.ValidacionOperacion;
import java.util.List;

public interface DAOValidacion {

    public List<ValidacionOperacion> obtenerValidaciones();
}

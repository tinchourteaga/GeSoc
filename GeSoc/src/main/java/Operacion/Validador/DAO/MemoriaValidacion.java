package Operacion.Validador.DAO;


import Operacion.Validador.ValidacionOperacion;

import java.util.ArrayList;
import java.util.List;

public class MemoriaValidacion implements DAOValidacion {
    @Override
    public List<ValidacionOperacion> obtenerValidaciones() {
        return new ArrayList<>();
    }
}

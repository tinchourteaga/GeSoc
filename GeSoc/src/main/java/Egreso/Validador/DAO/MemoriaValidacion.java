package Egreso.Validador.DAO;


import Egreso.Validador.ValidacionOperacion;
import Egreso.Validador.ValidarCantidadPresupuestos;
import Egreso.Validador.ValidarCompraPertenecePresupuesto;
import Egreso.Validador.ValidarCriterioProveedor;

import java.util.ArrayList;
import java.util.List;

public class MemoriaValidacion implements DAOValidacion {
    @Override
    public List<ValidacionOperacion> obtenerValidaciones() {
        return new ArrayList<ValidacionOperacion>(){
            {
                add(new ValidarCantidadPresupuestos());
                add(new ValidarCompraPertenecePresupuesto());
                add(new ValidarCriterioProveedor());
            }
        };
    }


}

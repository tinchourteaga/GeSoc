package Egreso.Validador.DAO;


import Egreso.Validador.Validaciones.ValidacionOperacion;
import Egreso.Validador.Validaciones.ValidarCantidadPresupuestos;
import Egreso.Validador.Validaciones.ValidarCompraPertenecePresupuesto;
import Egreso.Validador.Validaciones.ValidarCriterioProveedor;

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

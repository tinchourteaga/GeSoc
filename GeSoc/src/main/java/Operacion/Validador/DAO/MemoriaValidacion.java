package Operacion.Validador.DAO;


import Operacion.Validador.ValidacionOperacion;
import Operacion.Validador.ValidarCantidadPresupuestos;
import Operacion.Validador.ValidarCompraPertenecePresupuesto;
import Operacion.Validador.ValidarCriterioProveedor;

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

package Dominio.Egreso.Validador.DAO;

import Dominio.Egreso.Validador.Validaciones.ValidacionOperacion;
import Dominio.Egreso.Validador.Validaciones.ValidarCantidadPresupuestos;
import Dominio.Egreso.Validador.Validaciones.ValidarCompraPertenecePresupuesto;
import Dominio.Egreso.Validador.Validaciones.ValidarCriterioProveedor;

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

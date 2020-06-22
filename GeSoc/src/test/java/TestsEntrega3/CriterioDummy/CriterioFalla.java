package TestsEntrega3.CriterioDummy;


import Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Egreso.Core.Egreso;
import Egreso.Core.Proveedor;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;

import java.util.List;

//TODO ONLY FOR TESTING
public class CriterioFalla implements CriterioSeleccionProveedor {
    @Override
    public Proveedor seleccionarProveedor(List<Proveedor> proveedores) {
        return proveedores.get(0);
    }

    @Override
    public void validar(Egreso operacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {

        throw new NoCumpleValidacionDeCriterioException();
    }
}

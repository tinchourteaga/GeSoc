package TestsEntrega3.CriterioDummy;


import Dominio.Egreso.Core.CriteriosProveedor.CriterioSeleccionProveedor;
import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;

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

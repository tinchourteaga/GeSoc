package Egreso.Core.CriteriosProveedor;

import Egreso.Core.Egreso;
import Egreso.Core.Proveedor;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;

import java.util.List;

public interface CriterioSeleccionProveedor {

    Proveedor seleccionarProveedor(List<Proveedor> proveedores);

    void validar(Egreso operacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException;
}

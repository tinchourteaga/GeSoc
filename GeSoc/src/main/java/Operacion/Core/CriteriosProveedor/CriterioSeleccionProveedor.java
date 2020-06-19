package Operacion.Core.CriteriosProveedor;

import Operacion.Core.Operacion;
import Operacion.Core.Proveedor;
import Operacion.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Operacion.Validador.Excepciones.NoCumpleValidacionException;

import java.util.List;

public interface CriterioSeleccionProveedor {

    Proveedor seleccionarProveedor(List<Proveedor> proveedores);

    void validar(Operacion operacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException;
}

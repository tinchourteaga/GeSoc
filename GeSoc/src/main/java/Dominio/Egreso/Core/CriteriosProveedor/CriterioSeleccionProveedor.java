package Dominio.Egreso.Core.CriteriosProveedor;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;

import java.util.List;

public interface CriterioSeleccionProveedor {

    Proveedor seleccionarProveedor(List<Proveedor> proveedores);

    void validar(Egreso operacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException;

    Presupuesto seleccionarPresupuesto(List<Presupuesto> presupuestos);
}

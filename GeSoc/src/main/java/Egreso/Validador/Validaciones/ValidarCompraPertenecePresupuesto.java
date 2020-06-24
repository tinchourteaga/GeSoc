package Egreso.Validador.Validaciones;

import Egreso.Core.Egreso;
import Egreso.Core.Presupuesto;
import Egreso.Core.Proveedor;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;

import java.util.List;
import java.util.stream.Collectors;

public class ValidarCompraPertenecePresupuesto implements ValidacionOperacion {

    @Override
    public void validar(Egreso operacion) throws NoCumpleValidacionException {
        List<Proveedor> proveedores = operacion.getProveedores();
        List<Presupuesto> listaPresupuestos = proveedores.stream().map(prov -> prov.getPresupuesto()).collect(Collectors.toList());
        Presupuesto presupuestoSeleccionado = operacion.getProveedorSeleccionado().getPresupuesto();

        boolean flag = listaPresupuestos.stream().anyMatch(prespuesto -> prespuesto.equals(presupuestoSeleccionado));

        if(!flag){
            throw new NoCumpleValidacionException();
        }
    }
}

package Operacion.Validador;

import Operacion.Core.Operacion;
import Operacion.Core.Presupuesto;
import Operacion.Core.Proveedor;
import Operacion.Validador.Excepciones.NoCumpleValidacionException;

import java.util.List;
import java.util.stream.Collectors;

public class ValidarCompraPertenecePresupuesto implements ValidacionOperacion{

    @Override
    public void validar(Operacion operacion) throws NoCumpleValidacionException {
        List<Proveedor> proveedores = operacion.getProveedores();
        List<Presupuesto> listaPresupuestos = proveedores.stream().map(prov -> prov.getPresupuesto()).collect(Collectors.toList());
        Presupuesto presupuestoSeleccionado = operacion.getProveedorSeleccionado().getPresupuesto();

        boolean flag = listaPresupuestos.stream().anyMatch(prespuesto -> presupuestoSeleccionado.equals(presupuestoSeleccionado));

        if(!flag){
            throw new NoCumpleValidacionException();
        }
    }
}

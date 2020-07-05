package Dominio.Egreso.Validador.Validaciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
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

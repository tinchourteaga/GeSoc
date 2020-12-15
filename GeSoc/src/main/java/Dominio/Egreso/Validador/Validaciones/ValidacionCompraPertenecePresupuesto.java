package Dominio.Egreso.Validador.Validaciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;

import java.util.List;

public class ValidacionCompraPertenecePresupuesto implements ValidacionOperacion {

    public ValidacionCompraPertenecePresupuesto(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    private List<Proveedor> proveedores;
    @Override
    public void validar(Egreso operacion) throws NoCumpleValidacionException {



        List<Presupuesto> presupuestoSeleccionados = operacion.getPresupuestosAConsiderar();

        Presupuesto presupuestoSeleccionado = operacion.getCriterio().seleccionarPresupuesto(presupuestoSeleccionados);

        boolean flag = presupuestoSeleccionados.stream().anyMatch(prespuesto -> prespuesto.equals(presupuestoSeleccionado));

        if(!flag){
            throw new NoCumpleValidacionException();
        }
    }
}

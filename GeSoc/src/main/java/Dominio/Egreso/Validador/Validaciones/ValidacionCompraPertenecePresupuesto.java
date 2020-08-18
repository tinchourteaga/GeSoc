package Dominio.Egreso.Validador.Validaciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import java.util.List;
import java.util.stream.Collectors;

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

        List<List<Presupuesto>> listaPresupuestos = proveedores.stream().map(prov -> prov.getPresupuestos()).collect(Collectors.toList());

        List<Presupuesto> presupuestos = listaPresupuestos.stream().flatMap(lista->lista.stream()).collect(Collectors.toList());

        List<Presupuesto> presupuestoSeleccionados = operacion.getProveedorSeleccionado().getPresupuestos();

        Presupuesto presupuestoSeleccionado = operacion.getCriterio().seleccionarPresupuesto(presupuestoSeleccionados);

        boolean flag = listaPresupuestos.stream().anyMatch(prespuesto -> prespuesto.equals(presupuestoSeleccionado));

        if(!flag){
            throw new NoCumpleValidacionException();
        }
    }
}
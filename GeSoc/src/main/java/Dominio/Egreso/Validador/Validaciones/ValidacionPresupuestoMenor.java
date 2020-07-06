package Dominio.Egreso.Validador.Validaciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import java.util.List;
import java.util.stream.Collectors;

public class ValidacionPresupuestoMenor implements ValidacionOperacion {

    public ValidacionPresupuestoMenor(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    private List<Proveedor> proveedores;

    @Override
    public void validar(Egreso unaOperacion) throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {

        List<Presupuesto> presupuestoSeleccionados = unaOperacion.getProveedorSeleccionado().getPresupuestos();

        Presupuesto presupuestoSeleccionado = unaOperacion.getCriterio().seleccionarPresupuesto(presupuestoSeleccionados);

        List<List<Presupuesto>> listaPresupuestos = proveedores.stream().map(prov -> prov.getPresupuestos()).collect(Collectors.toList());

        List<Presupuesto> presupuestos = listaPresupuestos.stream().flatMap(lista->lista.stream()).collect(Collectors.toList());


        boolean flag = presupuestos.stream().allMatch(presupuesto -> presupuesto.getValor() >= presupuestoSeleccionado.getValor());
        if(!flag){
            throw new NoCumpleValidacionDeCriterioException();
        }
    }
}

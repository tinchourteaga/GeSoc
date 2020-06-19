package Egreso.Core.CriteriosProveedor;

import Egreso.Core.Egreso;
import Egreso.Core.Presupuesto;
import Egreso.Core.Proveedor;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Egreso.Validador.ValidacionOperacion;

import java.util.List;
import java.util.stream.Collectors;

public class CriterioMenorPrecio implements CriterioSeleccionProveedor{
    ValidacionOperacion validacionOperacion;

    @Override
    public Proveedor seleccionarProveedor(List<Proveedor> proveedores) {
        proveedores.sort((Proveedor prov1, Proveedor prov2) ->
                (int)(prov2.getPresupuesto().getValor() - prov1.getPresupuesto().getValor()));

        return proveedores.stream().findFirst().orElse(null);
    }

    @Override
    public void validar(Egreso operacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        List<Proveedor> proveedores = operacion.getProveedores();
        Proveedor proveedor = this.seleccionarProveedor(proveedores);
        Presupuesto presupuestoMasBarato = proveedor.getPresupuesto();

        List<Presupuesto> listaPresupuestos = proveedores.stream().map(prov -> prov.getPresupuesto()).collect(Collectors.toList());

        boolean flag = listaPresupuestos.stream().allMatch(presupuesto -> presupuesto.getValor() > presupuestoMasBarato.getValor());

        if(!flag){
            throw new NoCumpleValidacionDeCriterioException();
        }
    }

}

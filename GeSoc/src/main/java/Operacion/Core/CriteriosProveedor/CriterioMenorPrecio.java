package Operacion.Core.CriteriosProveedor;

import Operacion.Core.Operacion;
import Operacion.Core.Presupuesto;
import Operacion.Core.Proveedor;
import Operacion.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Operacion.Validador.Excepciones.NoCumpleValidacionException;
import Operacion.Validador.ValidacionOperacion;

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
    public void validar(Operacion operacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
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

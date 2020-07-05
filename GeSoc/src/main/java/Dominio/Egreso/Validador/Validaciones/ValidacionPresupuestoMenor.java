package Dominio.Egreso.Validador.Validaciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import java.util.List;
import java.util.stream.Collectors;

public class ValidacionPresupuestoMenor implements ValidacionOperacion {
    @Override
    public void validar(Egreso unaOperacion) throws NoCumpleValidacionException, NoCumpleValidacionDeCriterioException {
        List<Proveedor> proveedores = unaOperacion.getProveedores();
        Proveedor proveedor = unaOperacion.getProveedorSeleccionado();
        Presupuesto presupuestoMasBarato = proveedor.getPresupuesto();

        List<Presupuesto> listaPresupuestos = proveedores.stream().map(prov -> prov.getPresupuesto()).collect(Collectors.toList());

        boolean flag = listaPresupuestos.stream().allMatch(presupuesto -> presupuesto.getValor() >= presupuestoMasBarato.getValor());
        if(!flag){
            throw new NoCumpleValidacionDeCriterioException();
        }
    }
}

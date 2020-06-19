package Egreso.Validador;

import Egreso.Core.Egreso;
import Egreso.Core.Presupuesto;
import Egreso.Core.Proveedor;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;

import java.util.List;
import java.util.stream.Collectors;

public class ValidarCantidadPresupuestos implements ValidacionOperacion{
    Integer presupuestosRequeridos = 4;

    @Override
    public void validar(Egreso operacion) throws NoCumpleValidacionException {
        List<Proveedor> proveedores = operacion.getProveedores();
        List<Presupuesto> listaPresupuestos = proveedores.stream().map(prov -> prov.getPresupuesto()).collect(Collectors.toList());

        if(listaPresupuestos.size() < presupuestosRequeridos){
            throw new NoCumpleValidacionException();
        }
    }
}

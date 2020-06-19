package Operacion.Validador;

import Operacion.Core.Operacion;
import Operacion.Core.Presupuesto;
import Operacion.Core.Proveedor;
import Operacion.Validador.Excepciones.NoCumpleValidacionException;

import java.util.List;
import java.util.stream.Collectors;

public class ValidarCantidadPresupuestos implements ValidacionOperacion{
    Integer presupuestosRequeridos = 4;

    @Override
    public void validar(Operacion operacion) throws NoCumpleValidacionException {
        List<Proveedor> proveedores = operacion.getProveedores();
        List<Presupuesto> listaPresupuestos = proveedores.stream().map(prov -> prov.getPresupuesto()).collect(Collectors.toList());

        if(listaPresupuestos.size() < presupuestosRequeridos){
            throw new NoCumpleValidacionException();
        }
    }
}

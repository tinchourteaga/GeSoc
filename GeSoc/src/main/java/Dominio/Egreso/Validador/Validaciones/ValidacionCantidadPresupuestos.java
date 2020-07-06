package Dominio.Egreso.Validador.Validaciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import java.util.List;

public class ValidacionCantidadPresupuestos implements ValidacionOperacion {
    Integer presupuestosRequeridos = 4;

    public ValidacionCantidadPresupuestos(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    private List<Proveedor> proveedores;
    @Override
    public void validar(Egreso operacion) throws NoCumpleValidacionException {

        if(proveedores.size() < presupuestosRequeridos){
            throw new NoCumpleValidacionException();
        }
    }
}

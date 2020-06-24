package Egreso.Core.CriteriosProveedor;

import Egreso.Core.Egreso;
import Egreso.Core.Proveedor;
import Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Egreso.Validador.Validaciones.ValidacionPresupuestoMenor;
import Egreso.Validador.Validaciones.ValidacionOperacion;

import java.util.List;

public class CriterioMenorPrecio implements CriterioSeleccionProveedor{

    private ValidacionOperacion validacionOperacion;

    public CriterioMenorPrecio() {
        this.validacionOperacion = new ValidacionPresupuestoMenor();
    }

    @Override
    public Proveedor seleccionarProveedor(List<Proveedor> proveedores) {
        proveedores.sort((Proveedor prov1, Proveedor prov2) ->
                (int)(prov1.getPresupuesto().getValor() - prov2.getPresupuesto().getValor()));

        return proveedores.stream().findFirst().orElse(null);
    }

    @Override
    public void validar(Egreso operacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        validacionOperacion.validar(operacion);
    }

}

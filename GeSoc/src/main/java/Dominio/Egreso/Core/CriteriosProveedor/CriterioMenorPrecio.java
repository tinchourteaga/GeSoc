package Dominio.Egreso.Core.CriteriosProveedor;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;
import Dominio.Egreso.Validador.Validaciones.ValidacionPresupuestoMenor;
import Dominio.Egreso.Validador.Validaciones.ValidacionOperacion;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CriterioMenorPrecio implements CriterioSeleccionProveedor{

    private ValidacionOperacion validacionOperacion;

    public CriterioMenorPrecio() {
        this.validacionOperacion = new ValidacionPresupuestoMenor(new ArrayList<>());
    }

    @Override
    public Presupuesto seleccionarPresupuesto(List<Presupuesto> presupuestos) {
        presupuestos.sort((Presupuesto presu1, Presupuesto presu2) ->
                (int)(presu1.getValor() - presu2.getValor()));

        return presupuestos.stream().findFirst().orElse(null);
    }

    @Override
    public Proveedor seleccionarProveedor(List<Proveedor> proveedores) {

        ValidacionPresupuestoMenor validacion = (ValidacionPresupuestoMenor)validacionOperacion;
        validacion.getProveedores().addAll(proveedores);

        List<Presupuesto> listaPresupuestos = proveedores.stream().map(prov -> prov.getPresupuestoCriterio(this)).collect(Collectors.toList());
        Presupuesto presupuestoElegido = this.seleccionarPresupuesto(listaPresupuestos);
        return proveedores.stream().filter(proveedor->proveedor.getPresupuestos().contains(presupuestoElegido)).collect(Collectors.toList()).get(0);
    }

    @Override
    public void validar(Egreso operacion) throws NoCumpleValidacionDeCriterioException, NoCumpleValidacionException {
        validacionOperacion.validar(operacion);
    }

}

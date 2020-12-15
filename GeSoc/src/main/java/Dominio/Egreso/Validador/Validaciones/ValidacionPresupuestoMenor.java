package Dominio.Egreso.Validador.Validaciones;

import Dominio.Egreso.Core.Egreso;
import Dominio.Egreso.Core.Presupuesto;
import Dominio.Egreso.Core.Proveedor;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionDeCriterioException;
import Dominio.Egreso.Validador.Excepciones.NoCumpleValidacionException;

import java.util.List;

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


     boolean flag= ! unaOperacion.getPresupuestosAConsiderar().isEmpty();
     boolean flagPresupuesto;
     if(flag==false){
         flagPresupuesto= false;
     }else{
         List<Presupuesto>presupuestosPosibles=unaOperacion.getPresupuestosAConsiderar();
         presupuestosPosibles.sort((Presupuesto presu1, Presupuesto presu2) ->
                 (int)(presu1.getValor() - presu2.getValor()));
     flagPresupuesto= presupuestosPosibles.get(0).equals(unaOperacion.getPresupuestoPactado());

     }

        if(!flag && flagPresupuesto){
            throw new NoCumpleValidacionDeCriterioException();
        }
    }
}

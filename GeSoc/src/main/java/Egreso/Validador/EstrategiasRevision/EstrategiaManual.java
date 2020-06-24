package Egreso.Validador.EstrategiasRevision;

import Egreso.Core.Egreso;
import Egreso.Validador.ValidadorDeOperacion;

public class EstrategiaManual implements EstrategiaRevision {


    @Override
    public void revisar(Egreso operacion) {
        System.out.println("Vamos a ejecutar validaciones para la operacion " + operacion);
        ValidadorDeOperacion.validarDefault(operacion);
        if(operacion.isEstaVerificada()){
            System.out.println("La operacion se valido satifactoriamente" + operacion);
        }else{
            System.out.println("La operacion "+ operacion +
                    "no se valido correctamente. Para mas informacion revise la bandeja de mensajes.");
        }
    }
}

package API.Vinculacion;

import Dominio.Egreso.Core.Egreso;
import Dominio.Ingreso.Ingreso;

import java.io.IOException;
import java.util.List;

public class ControllerVinculacion {

    Vinculador unVinculador;

    private static ControllerVinculacion instancia=null;

    public static ControllerVinculacion obtenerInstacia(){
        if(instancia==null){
            instancia=new ControllerVinculacion();
        }
        return instancia;
    }

    public void vincular(List<Egreso> egresos, List<Ingreso> ingresos,List<String> criterios, List<Condicion> condiciones){
        try {
            unVinculador.vincular(egresos,ingresos,criterios,condiciones);
        } catch (IOException e) {
            e.printStackTrace();//sacar pantallita de error TODO
        }
    }

    public Vinculador getVinculador() {
        return unVinculador;
    }

    public void CambiarVinculador(Vinculador unVinculador) {
        this.unVinculador = unVinculador;
    }

    private ControllerVinculacion() {
        this.unVinculador = new VinculadorPropio();
    }
}

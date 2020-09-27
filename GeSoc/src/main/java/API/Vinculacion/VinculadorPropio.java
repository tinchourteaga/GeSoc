package API.Vinculacion;

import API.RequestMaker.RequestMaker;
import Dominio.Egreso.Core.Egreso;
import Dominio.Ingreso.Ingreso;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class VinculadorPropio implements Vinculador {

    private String urlDominio="";//poner el dominio aca
    @Override
    public void vincular(List<Egreso> egresos, List<Ingreso> ingresos,List<String> criterios, List<Condicion> condiciones) throws IOException {

        String json="{\"ingresos\" :[";
        String finalJson = json;
        for (int i=0;i<ingresos.size();i++){
                json= json.concat(new Gson().toJson(ingresos.get(i)));
        }
        json=json.concat("],");
        json=json.concat("\"egresos\" :[");
        for (int i=0;i<egresos.size();i++){
            json= json.concat(new Gson().toJson(egresos.get(i)));
        }
        json=json.concat("],");

        json=json.concat("\"criterios\" :[");
        for (int i=0;i<criterios.size();i++){
            json= json.concat(new Gson().toJson(criterios.get(i)));
        }
        json=json.concat("],");

        json=json.concat("\"condiciones\" :[");
        for (int i=0;i<condiciones.size();i++){
            json= json.concat(new Gson().toJson(condiciones.get(i)));
        }
        json=json.concat("]}");


        RequestMaker.getInstance().crearPOST(urlDominio,json);
    }
}

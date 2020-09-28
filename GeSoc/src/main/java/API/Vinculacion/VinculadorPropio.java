package API.Vinculacion;

import API.RequestMaker.RequestMaker;
import Dominio.Egreso.Core.Egreso;
import Dominio.Ingreso.Ingreso;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class VinculadorPropio implements Vinculador {

    private String urlDominio="http://localhost:5000/api/add_message/1234";//poner el dominio aca

    //Dominio Python: http://localhost:5000/api/add_message/1234
    //Dominio Java2: http://localhost:4575/

    @Override
    public void vincular(List<Egreso> egresos, List<Ingreso> ingresos,List<String> criterios, List<Condicion> condiciones) throws IOException {

        String json="{\"ingresos\" :[";
        String finalJson = json;

        if(0<ingresos.size())
            json= json.concat(new Gson().toJson(ingresos.get(0)));

        for (int i=1;i<ingresos.size();i++){
                json= json + ",";
                json= json.concat(new Gson().toJson(ingresos.get(i)));

        }
        json=json.concat("],");
        json=json.concat("\"egresos\" :[");

        if(0<egresos.size())
            json= json.concat(new Gson().toJson(egresos.get(0)));

        for (int i=1;i<egresos.size();i++){
            json= json + ",";
            json= json.concat(new Gson().toJson(egresos.get(i)));
        }
        json=json.concat("],");

        json=json.concat("\"criterios\" :[");

        if(0<criterios.size())
            json= json.concat(new Gson().toJson(criterios.get(0)));

        for (int i=1;i<criterios.size();i++){
            json= json + ",";
            json= json.concat(new Gson().toJson(criterios.get(i)));
        }
        json=json.concat("],");

        json=json.concat("\"condiciones\" :[");

        if(0<condiciones.size())
            json= json.concat(new Gson().toJson(condiciones.get(0)));

        for (int i=1;i<condiciones.size();i++){
            json= json + ",";
            json= json.concat(new Gson().toJson(condiciones.get(i)));
        }
        json=json.concat("]}");


        RequestMaker.getInstance().crearPOST(urlDominio,json);
    }
}

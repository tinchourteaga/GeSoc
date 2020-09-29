package API.Vinculacion;

import API.RequestMaker.RequestMaker;
import Dominio.Egreso.Core.Egreso;
import Dominio.Ingreso.Excepciones.NoPuedoAsignarMasDineroDelQueTengoException;
import Dominio.Ingreso.Ingreso;
import com.google.gson.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VinculadorPropio implements Vinculador {

    private String urlDominio="http://localhost:5000/api/add_message/1234";//poner el dominio aca

    //Dominio Python: http://localhost:5000/api/add_message/1234
    //Dominio Java2: http://localhost:4575/

    @Override
    public void vincular(List<Egreso> egresos, List<Ingreso> ingresos,List<String> criterios, List<Condicion> condiciones) throws IOException {

        String json=this.generarJson(egresos,ingresos,criterios,condiciones);
        HttpEntity respuesta= RequestMaker.getInstance().crearPOST(urlDominio,json);
        leerJson(respuesta,egresos,ingresos);
    }

    private void leerJson(HttpEntity respuesta, List<Egreso> egresos, List<Ingreso> ingresos) throws IOException {
        String responseStr = IOUtils.toString(respuesta.getContent(), "UTF-8");
        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonArray responseObj = parser.parse(responseStr).getAsJsonArray();
            responseObj.forEach(jsonElemnt-> generarVinculacion(jsonElemnt,egresos,ingresos));
        }
    }

    private void generarVinculacion(JsonElement jsonElemnt, List<Egreso> egresos, List<Ingreso> ingresos) {


        System.out.println(jsonElemnt);
        System.out.println(jsonElemnt.isJsonPrimitive());
        System.out.println(jsonElemnt.isJsonObject());

        JsonObject objetito = (JsonObject) new JsonParser().parse(jsonElemnt.getAsString());
        Integer id_movimiento=objetito.get("movimiento-asociado").getAsInt();
        String lista = (objetito.get("vinculados").getAsString());
        List<Integer> ids_asociados=obtenerListaDeIntsFromString(lista);

        String tipoVinculacion=objetito.getAsJsonObject().get("criterio").getAsString();
        if(tipoVinculacion.equals("OrdenValorPrimerIngreso")){
         reflejarVinculacionEgresoIngreso(id_movimiento, ids_asociados,egresos,ingresos);
        }else {
            reflejarVinculacionIngresoEgreso(id_movimiento, ids_asociados,egresos,ingresos);
        }
    }

    private List<Integer> obtenerListaDeIntsFromString(String lista) {
        List<Integer> retorno=new ArrayList();

        String listaSinCorchetes=lista.subSequence(1,lista.length()).toString();
        String[] magia= listaSinCorchetes.split(",");
        for (int i=0; i<magia.length;i++){
        retorno.add(Integer.valueOf(magia[i]));
        }

        return retorno;
    }

    private void reflejarVinculacionIngresoEgreso(Integer id_movimiento, List<Integer> ids_asociados, List<Egreso> egresos, List<Ingreso> ingresos) {

        List<Ingreso> ingresosVinculados=ingresos.stream().filter(ingreso->ids_asociados.contains(ingreso.getIngreso())).collect(Collectors.toList());

        List<Egreso> egresosAux = egresos.stream().filter(egreso->egreso.getEgreso()==id_movimiento).collect(Collectors.toList());

        if(!egresosAux.isEmpty()){
            Egreso egresoVinculado = egresosAux.get(0);
        ingresosVinculados.forEach(ingresoAVincular-> {
            try {
                ingresoAVincular.agregarEgreso(egresoVinculado);
            } catch (NoPuedoAsignarMasDineroDelQueTengoException e) {
                e.printStackTrace();//no deberia pasar esto a menos que la api este mal
            }
        });}
    }

    private void reflejarVinculacionEgresoIngreso(Integer id_movimiento, List<Integer> ids_asociados, List<Egreso> egresos, List<Ingreso> ingresos) {

        List<Egreso> egresosVinculados=egresos.stream().filter(egreso->ids_asociados.contains(egreso.getEgreso())).collect(Collectors.toList());

        List<Ingreso> ingresosAux = ingresos.stream().filter(egreso->egreso.getIngreso()==id_movimiento).collect(Collectors.toList());

        if(!ingresosAux.isEmpty()){
        Ingreso ingresoVinculado = ingresosAux.get(0);
        egresosVinculados.forEach(egresoAVincular-> {
            try {
                ingresoVinculado.agregarEgreso(egresoAVincular);
            } catch (NoPuedoAsignarMasDineroDelQueTengoException e) {
                e.printStackTrace();//no deberia pasar esto a menos que la api este mal
            }
        });}

    }

    private String generarJson(List<Egreso> egresos, List<Ingreso> ingresos, List<String> criterios, List<Condicion> condiciones) {
        String json="{\"ingresos\" :[";

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
        return json;
    }

    class Vinculacion{
        List<Ingreso> ingresos=new ArrayList<>();
        List<Egreso> egresos=new ArrayList<>();
        List<Condicion> condiciones=new ArrayList();
        List<String> criterios=new ArrayList<>();

        public Vinculacion(List<Ingreso> ingresos, List<Egreso> egresos, List<Condicion> condiciones, List<String> criterios) {
            this.ingresos = ingresos;
            this.egresos = egresos;
            this.condiciones = condiciones;
            this.criterios = criterios;
        }
    }
}

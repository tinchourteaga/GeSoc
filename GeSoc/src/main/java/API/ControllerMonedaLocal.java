package API;

import API.DTOs.MonedaDTO;
import API.DTOs.NombreYID;
import API.DTOs.PaisDTO;
import API.DTOs.ZipCodeDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ControllerMonedaLocal {

    private String urlDominio="https://api.mercadolibre.com";

    private static ControllerMonedaLocal instancia=null;

    private List<PaisDTO> paises=new ArrayList<PaisDTO>();
    private List<MonedaDTO> monedas=new ArrayList<MonedaDTO>();

    private ControllerMonedaLocal() throws IOException {
        pedirPaises();
        pedirMonedas();
    }

    private ZipCodeDTO pedirInformacionCodigoPostal(String nombrePais, String zipCode) throws IOException {

        PaisDTO paisBuscado=paises.stream().filter(pais->pais.getName().equals(nombrePais)).collect(Collectors.toList()).get(0);

        String request="/countries/"+paisBuscado.getId() +"/zip_codes/"+zipCode;
        HttpEntity entidad= crearRequest(request);
        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");
        ZipCodeDTO zipCodeObj=null;
        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject responseObj = parser.parse(responseStr).getAsJsonObject();
            zipCodeObj=crearDTOZipCode(responseObj);
        }

        return zipCodeObj;
    }

    private ZipCodeDTO crearDTOZipCode(JsonObject responseObj) {

        int zipCode=responseObj.get("zip_code").getAsInt();
        JsonObject stateJson= responseObj.getAsJsonObject("state");
        NombreYID state=new NombreYID(stateJson.get("name").getAsString(),stateJson.get("id").getAsString());
        JsonObject countryJson= responseObj.getAsJsonObject("country");
        NombreYID country=new NombreYID(countryJson.get("name").getAsString(),countryJson.get("id").getAsString());
        JsonObject cityJson= responseObj.getAsJsonObject("city");
        NombreYID city=new NombreYID(cityJson.get("name").getAsString(),cityJson.get("id").getAsString());

        return new ZipCodeDTO(state,country,zipCode,city);
    }

    private void pedirMonedas() throws IOException {
        HttpEntity entidad= crearRequest("/currencies");
        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");
        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonArray responseObj = parser.parse(responseStr).getAsJsonArray();
            responseObj.forEach(jsonElemnt-> monedas.add(new MonedaDTO(jsonElemnt.getAsJsonObject().get("symbol").toString(),jsonElemnt.getAsJsonObject().get("decimal_places").getAsInt(),jsonElemnt.getAsJsonObject().get("description").toString(),jsonElemnt.getAsJsonObject().get("id").toString())));
        }

    }

    private HttpEntity crearRequest(String pedido) throws IOException {
        String url = urlDominio+pedido;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;
        resp = client.execute(get);
        return resp.getEntity();
    }

    private void pedirPaises() throws IOException {
        HttpEntity entidad = crearRequest("/classified_locations/countries");

        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");
        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonArray responseObj = parser.parse(responseStr).getAsJsonArray();
            responseObj.forEach(jsonElemnt -> paises.add(new PaisDTO(jsonElemnt.getAsJsonObject().get("id").toString(), jsonElemnt.getAsJsonObject().get("name").toString(), jsonElemnt.getAsJsonObject().get("locale").toString(), jsonElemnt.getAsJsonObject().get("currency_id").toString())));
        }
    }
    public ControllerMonedaLocal getControllerMonedaLocal(){
        if(instancia==null){
            try {
                instancia=new ControllerMonedaLocal();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instancia;
    }

    private PaisDTO getPais(String nombrePais) {
        return paises.stream().filter(pais->pais.getName().equals(nombrePais)).collect(Collectors.toList()).get(0);
    }

    private MonedaDTO getMoneda(String nombreMoneda) {
        return monedas.stream().filter(moneda->moneda.getDescription().equals(nombreMoneda)).collect(Collectors.toList()).get(0);
    }

}

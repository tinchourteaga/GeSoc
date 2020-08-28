package API;

import API.DTOs.*;
import API.Excepciones.ExcepcionCodigoNoEncontrado;
import API.Excepciones.ExcepcionNoSePudoConvertir;
import API.Excepciones.NoExisteMonedaException;
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


public class ControllerMercadoLibre {

    private String urlDominio="https://api.mercadolibre.com";

    private static ControllerMercadoLibre instancia=null;

    private List<PaisDTO> paises=new ArrayList<PaisDTO>();
    private List<MonedaDTO> monedas=new ArrayList<MonedaDTO>();

    private ControllerMercadoLibre() throws IOException {
        pedirPaises();
        pedirMonedas();
    }

    public ConversionDTO convertirMoneda(String nombreMonedaActual, String nombreMonedaAConvertir) throws IOException, ExcepcionNoSePudoConvertir, NoExisteMonedaException {

        MonedaDTO monedaActual=getMoneda(nombreMonedaActual);
        MonedaDTO monedaAConvertir=getMoneda(nombreMonedaAConvertir);
        if(monedaActual==null){
            throw new NoExisteMonedaException(nombreMonedaActual);
        }
        if(nombreMonedaAConvertir==null){

            throw new NoExisteMonedaException(nombreMonedaAConvertir);
        }
        String request="/currency_conversions/search?from="+monedaActual.getId()+"&to="+monedaAConvertir.getId();
        HttpEntity entidad= crearRequest(request);
        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");
        ConversionDTO conversion=null;
        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject responseObj = parser.parse(responseStr).getAsJsonObject();
            conversion=crearDTOConversion(responseObj,monedaActual,monedaAConvertir);
        }else{

            throw new ExcepcionNoSePudoConvertir(nombreMonedaActual,nombreMonedaAConvertir);
        }

        return conversion;


    }

    private ConversionDTO crearDTOConversion(JsonObject responseObj, MonedaDTO monedaActual, MonedaDTO monedaAConvertir) {

        JsonObject ratio= responseObj.getAsJsonObject("ratio");
        JsonObject ratioMercadoPago= responseObj.getAsJsonObject("mercado_pago_ratio");
        return new ConversionDTO(monedaActual,monedaAConvertir,ratio.getAsDouble(),ratioMercadoPago.getAsDouble());
    }


    public ZipCodeDTO pedirInformacionCodigoPostal(Pais nombrePais, String zipCode) throws IOException, ExcepcionCodigoNoEncontrado {

        PaisDTO paisBuscado=paises.stream().filter(pais->pais.getName().equals(nombrePais.getName())).collect(Collectors.toList()).get(0);

        String request="/countries/"+paisBuscado.getId() +"/zip_codes/"+zipCode;
        HttpEntity entidad= crearRequest(request);
        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");
        ZipCodeDTO zipCodeObj=null;
        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject responseObj = parser.parse(responseStr).getAsJsonObject();
            zipCodeObj=crearDTOZipCode(responseObj);
        }else{

            throw new ExcepcionCodigoNoEncontrado(nombrePais.getName(),zipCode);
        }

        return zipCodeObj;
    }

    private ZipCodeDTO crearDTOZipCode(JsonObject responseObj) {

        int zipCode=responseObj.get("zip_code").getAsInt();
        JsonObject stateJson= responseObj.getAsJsonObject("state");
        Provincia state=new Provincia(stateJson.get("id").getAsString(),stateJson.get("name").getAsString());
        JsonObject countryJson= responseObj.getAsJsonObject("country");
        Pais country=new Pais(countryJson.get("id").getAsString(),countryJson.get("name").getAsString());
        JsonObject cityJson= responseObj.getAsJsonObject("city");
        Ciudad city=new Ciudad(cityJson.get("id").getAsString(),cityJson.get("name").getAsString());

        return new ZipCodeDTO(state,country,zipCode,city);
    }

    private void pedirMonedas() throws IOException {
        HttpEntity entidad= crearRequest("/currencies");
        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");
        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonArray responseObj = parser.parse(responseStr).getAsJsonArray();
            responseObj.forEach(jsonElemnt-> monedas.add(new MonedaDTO(
                    jsonElemnt.getAsJsonObject().get("symbol").getAsString(),
                    jsonElemnt.getAsJsonObject().get("decimal_places").getAsInt(),
                    jsonElemnt.getAsJsonObject().get("description").getAsString(),
                    jsonElemnt.getAsJsonObject().get("id").getAsString())));
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
            responseObj.forEach(jsonElemnt -> paises.add(new PaisDTO(
                    jsonElemnt.getAsJsonObject().get("id").getAsString(),
                    jsonElemnt.getAsJsonObject().get("name").getAsString(),
                    jsonElemnt.getAsJsonObject().get("locale").getAsString(),
                    jsonElemnt.getAsJsonObject().get("currency_id").getAsString())));
            
        }
    }
    public static ControllerMercadoLibre getControllerMercadoLibre(){
        if(instancia==null){
            try {
                instancia=new ControllerMercadoLibre();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instancia;
    }

    public PaisDTO getPais(String nombrePais) {
        return paises.stream().filter(pais->pais.getName().equals(nombrePais)).collect(Collectors.toList()).get(0);
    }

    public MonedaDTO getMoneda(String nombreMoneda) {
        return monedas.stream().filter(moneda->moneda.getDescription().equals(nombreMoneda)).collect(Collectors.toList()).get(0);
    }

    public MonedaDTO getMonedaByID(String idMoneda) {
        return monedas.stream().filter(moneda->moneda.getId().equals(idMoneda)).collect(Collectors.toList()).get(0);
    }

}

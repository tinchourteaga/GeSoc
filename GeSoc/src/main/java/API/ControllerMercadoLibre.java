package API;

import API.DTOs.*;
import API.Excepciones.*;
import Lugares.Ciudad;
import Lugares.Pais;
import Lugares.Provincia;
import Persistencia.Repositorio;
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
    private List<ProvinciaDTO> provincias=new ArrayList<ProvinciaDTO>();
    private List<CiudadDTO> ciudades=new ArrayList<>();
    private List<MonedaDTO> monedas=new ArrayList<MonedaDTO>();
    private Repositorio paisesRepo = new Repositorio();
    private Repositorio provinciasRepo = new Repositorio();
    private Repositorio ciudadesRepo = new Repositorio();

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
            responseObj.forEach(jsonElemnt -> {
                try {
                    paises.add(new PaisDTO(
                            jsonElemnt.getAsJsonObject().get("id").getAsString(),
                            jsonElemnt.getAsJsonObject().get("name").getAsString(),
                            jsonElemnt.getAsJsonObject().get("locale").getAsString(),
                            jsonElemnt.getAsJsonObject().get("currency_id").getAsString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            
        }
    }

    public List<ProvinciaDTO> obtenerProvinciasDe(String id_pais) throws IOException {
        List<ProvinciaDTO>lista=new ArrayList<>();

        HttpEntity entidad = crearRequest("/classified_locations/countries/"+id_pais);

        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");
        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject responseObj = parser.parse(responseStr).getAsJsonObject();
            responseObj.getAsJsonArray("states").forEach(state-> {
                try {
                    lista.add(obtenerDatosProvincia( state.getAsJsonObject().get("id").getAsString()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ExcepcionProvinciaNoEncontrada excepcionProvinciaNoEncontrada) {
                    excepcionProvinciaNoEncontrada.printStackTrace();
                }
            });
        }
        return lista;
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

    private ConversionDTO crearDTOConversion(JsonObject responseObj, MonedaDTO monedaActual, MonedaDTO monedaAConvertir) {

        JsonObject ratio= responseObj.getAsJsonObject("ratio");
        JsonObject ratioMercadoPago= responseObj.getAsJsonObject("mercado_pago_ratio");
        return new ConversionDTO(monedaActual,monedaAConvertir,ratio.getAsDouble(),ratioMercadoPago.getAsDouble());
    }

    //PROVINCIAS

    private ProvinciaDTO crearDTOProvincia(JsonObject responseObj) {
        JsonObject id= responseObj.getAsJsonObject("id");
        JsonObject nombre= responseObj.getAsJsonObject("name");
        JsonArray ciudades= responseObj.getAsJsonArray("cities");
        ProvinciaDTO nuevaProvincia= new ProvinciaDTO(id.getAsString(),nombre.getAsString());
        return nuevaProvincia;
    }

    private ProvinciaDTO obtenerDatosProvincia(String id) throws IOException, ExcepcionProvinciaNoEncontrada {

        String request="/classified_locations/states/"+id;
        HttpEntity entidad = crearRequest(request);
        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");
        ProvinciaDTO nuevaProvincia = null;
        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject responseObj = parser.parse(responseStr).getAsJsonObject();
            nuevaProvincia=crearDTOProvincia(responseObj);
        }else{

            throw new ExcepcionProvinciaNoEncontrada(id);
        }

        return nuevaProvincia;
    }

    public Provincia generarDatosDeProvincia(String idProvincia) throws IOException, ExcepcionProvinciaNoEncontrada {

        ProvinciaDTO busqueda = provincias.stream().filter(provincia->provincia.getId().equals(idProvincia)).collect(Collectors.toList()).get(0);
        Provincia retorno=null;
        if(busqueda==null) {
            busqueda= obtenerDatosProvincia(idProvincia);
            provincias.add(busqueda);
        }
        retorno=new Provincia(busqueda.getId(),busqueda.getName());
        return retorno;
    }

    public List<ProvinciaDTO> obtenerLasProviciasDeUnPais(String idPais) throws IOException {

        List<ProvinciaDTO> retorno = new ArrayList<ProvinciaDTO>();

        HttpEntity entidad = crearRequest("/classified_locations/countries/" + idPais);

        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");

        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject responseObj = parser.parse(responseStr).getAsJsonObject();

            responseObj.getAsJsonArray("states").forEach
                    (x->retorno.add(new ProvinciaDTO(x.getAsJsonObject().get("id").getAsString(),
                            x.getAsJsonObject().get("name").getAsString())));
        }

        return retorno;
    }

    //CIUDADES

    private CiudadDTO crearDTOCiudad(JsonObject responseObj) {
        JsonObject id= responseObj.getAsJsonObject("id");
        JsonObject nombre= responseObj.getAsJsonObject("name");
        JsonArray ciudades= responseObj.getAsJsonArray("cities");
        CiudadDTO nuevaCiudad= new CiudadDTO(id.getAsString(),nombre.getAsString());
        return nuevaCiudad;
    }

    private CiudadDTO obtenerDatosCiudad(String id) throws IOException, ExcepcionCiudadNoEncontrada {

        String request="/classified_locations/cities/"+id;
        HttpEntity entidad = crearRequest(request);
        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");
        CiudadDTO nuevaCiudad = null;
        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject responseObj = parser.parse(responseStr).getAsJsonObject();
            nuevaCiudad=crearDTOCiudad(responseObj);
        }else{

            throw new ExcepcionCiudadNoEncontrada(id);
        }

        return nuevaCiudad;
    }

    public Ciudad generarDatosDeCiudad(String idCiudad) throws IOException, ExcepcionCiudadNoEncontrada {

        CiudadDTO busqueda = ciudades.stream().filter(ciudad->ciudad.getId().equals(idCiudad)).collect(Collectors.toList()).get(0);
        Ciudad retorno=null;
        if(busqueda==null) {
            busqueda= obtenerDatosCiudad(idCiudad);
            ciudades.add(busqueda);
        }
        retorno=new Ciudad(busqueda.getId(),busqueda.getName());
        return retorno;
    }

    public List<CiudadDTO> obtenerLasCiudadesDeUnaProvincia(String idProvincia) throws IOException {

        List<CiudadDTO> retorno = new ArrayList<CiudadDTO>();

        HttpEntity entidad = crearRequest("/classified_locations/states/" + idProvincia);

        String responseStr = IOUtils.toString(entidad.getContent(), "UTF-8");

        if (responseStr != null && !responseStr.isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonObject responseObj = parser.parse(responseStr).getAsJsonObject();

            responseObj.getAsJsonArray("states").forEach
                    (x->retorno.add(new CiudadDTO(x.getAsJsonObject().get("id").getAsString(),
                            x.getAsJsonObject().get("name").getAsString())));
        }

        return retorno;
    }
}

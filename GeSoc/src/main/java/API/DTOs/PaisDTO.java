package API.DTOs;

import API.ControllerMercadoLibre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaisDTO {
     private String id;
     private String name;
     private String locale;
     private String currency_id;
     private List<ProvinciaDTO>provincias;

    public PaisDTO(String id, String name, String locale, String currency_id) throws IOException {
        this.id = id;
        this.name = name;
        this.locale = locale;
        this.currency_id = currency_id;
        provincias= ControllerMercadoLibre.getControllerMercadoLibre().obtenerProvinciasDe(id);
    }

    public void agregarProvincia(ProvinciaDTO unaProvincia){provincias.add(unaProvincia);}
    public void removerProvincia(ProvinciaDTO unaProvincia){provincias.remove(unaProvincia);}

    public List<ProvinciaDTO> getProvincias() {
        return provincias;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLocale() {
        return locale;
    }
    public String getCurrency_id() {
        return currency_id;
    }
}

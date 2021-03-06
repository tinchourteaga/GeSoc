package API.ML.DTOs;

import Persistencia.InterfacesPersistencia.IdPersistedClass;
import Persistencia.InterfacesPersistencia.NamePersistedClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaisDTO  implements IdPersistedClass, NamePersistedClass {
    private String id;
    private String name;
    private String locale;
    private String currency_id;
    private List<ProvinciaDTO> provincias = new ArrayList<>();

    public PaisDTO(String id, String name, String locale, String currency_id) throws IOException {
        this.id = id;
        this.name = name;
        this.locale = locale;
        this.currency_id = currency_id;
    }

    @Override
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
    public List<ProvinciaDTO> getProvincias() { return provincias; }

    @Override
    public String getNombre() {
        return name;
    }
}

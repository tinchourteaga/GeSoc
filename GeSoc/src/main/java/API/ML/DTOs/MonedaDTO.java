package API.ML.DTOs;

import Persistencia.InterfacesPersistencia.IdPersistedClass;
import Persistencia.InterfacesPersistencia.NamePersistedClass;

public class MonedaDTO implements IdPersistedClass, NamePersistedClass {
    private String symbol;
    private int decimal_places;
    private String description;
    private String id;

    public MonedaDTO(String symbol, int decimal_places, String description, String id) {
        this.symbol = symbol;
        this.decimal_places = decimal_places;
        this.description = description;
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }
    public int getDecimal_places() {
        return decimal_places;
    }
    public String getDescription() {
        return description;
    }
   @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return description;
    }
}

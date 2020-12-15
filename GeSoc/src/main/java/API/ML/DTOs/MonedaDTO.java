package API.ML.DTOs;

public class MonedaDTO {

    private int moneda;

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

    public MonedaDTO() {

    }

    public int getMoneda() {
        return moneda;
    }

    public void setMoneda(int moneda) {
        this.moneda = moneda;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getDecimal_places() {
        return decimal_places;
    }

    public void setDecimal_places(int decimal_places) {
        this.decimal_places = decimal_places;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package API.DTOs;

public class MonedaDTO {
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
    public String getId() {
        return id;
    }
}

package API.ML.DTOs;

import Persistencia.InterfacesPersistencia.IdPersistedClass;
import Persistencia.InterfacesPersistencia.NamePersistedClass;

import javax.persistence.*;

@Entity
@Table(name = "dom_moneda")
public class MonedaDTO implements IdPersistedClass, NamePersistedClass {

    @Id
    @GeneratedValue
    private int moneda;

    @Column(name="simbolo")
    private String symbol;

    @Column(name="decimales")
    private int decimal_places;

    @Column(name="desc")
    private String description;

    @Column(name="id_mercado_libre")
    private String id;

    public MonedaDTO(String symbol, int decimal_places, String description, String id) {
        this.symbol = symbol;
        this.decimal_places = decimal_places;
        this.description = description;
        this.id = id;
    }

    public MonedaDTO() {

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

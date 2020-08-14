package API.DTOs;

import java.util.ArrayList;
import java.util.List;

public class PaisDTO {
     private String id;
     private String name;
     private String locale;
     private String currency_id;

    public PaisDTO(String id, String name, String locale, String currency_id) {
        this.id = id;
        this.name = name;
        this.locale = locale;
        this.currency_id = currency_id;
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

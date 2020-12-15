package Lugares;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "geo_paises")
public class Pais {
    @Id
    @GeneratedValue
    private int pais;

    @Column(name="id_mercado_libre")
    String id;

    @Column(name = "nombre")
    String name;

    @Transient
    private String locale;
    @Transient
    private String currency_id;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "pais")
    private List<Provincia> provincias = new ArrayList<>();

    public Pais() { }

    public Pais(String id, String name, String locale, String currency_id) {
        this.id = id;
        this.name = name;
        this.locale = locale;
        this.currency_id = currency_id;
    }

    public Pais(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getPais() {
        return pais;
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
    public List<Provincia> getProvincias() { return provincias; }
}
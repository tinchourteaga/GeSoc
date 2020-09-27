package Lugares;

import javax.persistence.*;

@Entity
@Table(name = "geo_ciudades")

public class Ciudad {
    @Id
    @GeneratedValue
    private int ciudad;

    @Transient
    String id;

    @Column(name = "nombre")
    String name;

    @ManyToOne
    @JoinColumn(name = "provincia", referencedColumnName = "provincia")
    private Provincia provincia;

    public Ciudad (String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }
}

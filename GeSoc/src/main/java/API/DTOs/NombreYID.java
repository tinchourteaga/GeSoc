package API.DTOs;

public class NombreYID {

    private String name;
    private String id;

    public NombreYID(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}

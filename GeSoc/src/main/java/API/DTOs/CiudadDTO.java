package API.DTOs;

public class CiudadDTO {
    private String id;
    private String name;

    public CiudadDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

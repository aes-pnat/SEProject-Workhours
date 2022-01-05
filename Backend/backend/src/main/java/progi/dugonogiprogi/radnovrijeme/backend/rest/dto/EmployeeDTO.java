package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

public class EmployeeDTO {

    private String name;
    private String id;

    public EmployeeDTO(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

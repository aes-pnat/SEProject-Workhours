package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

public class JobDTO {
    String name;
    String description;

    public JobDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }
}

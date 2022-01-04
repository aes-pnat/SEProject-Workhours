package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

public class JobDTO {

    private String name;
    private Integer id;

    public JobDTO(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}

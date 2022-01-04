package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

public class LocationDTO {

    private String address;
    private Integer id;

    public LocationDTO(String address, Integer id) {
        this.address = address;
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public Integer getId() {
        return id;
    }
}

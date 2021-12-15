package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

public class RegistrationDTO {

    private String pid;

    private String name;

    private String surname;

    private String email;

    private String username;

    private String password;

    private String passwordCheck;

    public String getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }
}

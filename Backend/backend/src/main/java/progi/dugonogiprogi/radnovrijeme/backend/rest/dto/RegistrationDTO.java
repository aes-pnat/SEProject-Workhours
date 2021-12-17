package progi.dugonogiprogi.radnovrijeme.backend.rest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegistrationDTO {

    @NotEmpty(message = "Pid should not be empty.")
    @Size(min = 11, max = 11, message = "Pid should be 11 characters long.")
    private String pid;

    @NotEmpty(message = "Name should not be empty.")
    private String name;

    @NotEmpty(message = "Surname should not be empty.")
    private String surname;

    @NotEmpty(message = "Email should not be empty.")
    private String email;

    @NotEmpty(message = "Username should not be empty.")
    private String username;

    @NotEmpty(message = "Password should not be empty.")
    private String password;

    @NotEmpty(message = "Password check should not be empty.")
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

package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "pid", nullable = false, length = 11)
    private String id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idrole", nullable = false)
    private Role idrole;

    public Role getIdrole() {
        return idrole;
    }

    public void setIdrole(Role idrole) {
        this.idrole = idrole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee() {

    }

    public Employee(String id, String name, String surname, String email, String password, String username, Role idrole) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.idrole = idrole;
    }
}
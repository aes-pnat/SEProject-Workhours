package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Table(name = "job")
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjob", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    @NotEmpty(message = "Name of the job should not be empty.")
    private String name;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price of the job should be defined.")
    private Double price;

    @Column(name = "hourprice", nullable = false)
    @NotNull(message = "Hour price of the job should be defined.")
    private Double hourprice;

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getHourprice() {
        return hourprice;
    }

    public void setHourprice(Double hourprice) {
        this.hourprice = hourprice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
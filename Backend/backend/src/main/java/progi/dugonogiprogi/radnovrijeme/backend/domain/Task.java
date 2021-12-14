package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtask", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "datetimestart", nullable = false)
    private Instant datetimestart;

    @Column(name = "datetimeend", nullable = false)
    private Instant datetimeend;

    @Column(name = "hoursneededestimate")
    private Integer hoursneededestimate;

    @Column(name = "plannedprofit")
    private Double plannedprofit;

    @Column(name = "realizedprofit")
    private Double realizedprofit;

    @Column(name = "plannedcost")
    private Double plannedcost;

    @Column(name = "realizedcost")
    private Double realizedcost;

    @ManyToOne
    @JoinColumn(name = "idjob")
    private Job idjob;

    @ManyToOne
    @JoinColumn(name = "idlocation")
    private Location idlocation;

    public Location getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(Location idlocation) {
        this.idlocation = idlocation;
    }

    public Job getIdjob() {
        return idjob;
    }

    public void setIdjob(Job idjob) {
        this.idjob = idjob;
    }

    public Double getRealizedcost() {
        return realizedcost;
    }

    public void setRealizedcost(Double realizedcost) {
        this.realizedcost = realizedcost;
    }

    public Double getPlannedcost() {
        return plannedcost;
    }

    public void setPlannedcost(Double plannedcost) {
        this.plannedcost = plannedcost;
    }

    public Double getRealizedprofit() {
        return realizedprofit;
    }

    public void setRealizedprofit(Double realizedprofit) {
        this.realizedprofit = realizedprofit;
    }

    public Double getPlannedprofit() {
        return plannedprofit;
    }

    public void setPlannedprofit(Double plannedprofit) {
        this.plannedprofit = plannedprofit;
    }

    public Integer getHoursneededestimate() {
        return hoursneededestimate;
    }

    public void setHoursneededestimate(Integer hoursneededestimate) {
        this.hoursneededestimate = hoursneededestimate;
    }

    public Instant getDatetimeend() {
        return datetimeend;
    }

    public void setDatetimeend(Instant datetimeend) {
        this.datetimeend = datetimeend;
    }

    public Instant getDatetimestart() {
        return datetimestart;
    }

    public void setDatetimestart(Instant datetimestart) {
        this.datetimestart = datetimestart;
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
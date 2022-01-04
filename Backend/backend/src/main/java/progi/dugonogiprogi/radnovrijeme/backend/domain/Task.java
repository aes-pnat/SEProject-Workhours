package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "task")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtask", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "datetimestart", nullable = false)
    private Instant datetimestart;

    @Column(name = "datetimeend", nullable = false)
    private Instant datetimeend;

    @Column(name = "hoursneededestimate")
    private Integer hoursneededestimate;

    @ManyToOne
    @JoinColumn(name = "idjob", nullable = false)
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

    public Task() {
    }

    public Task(String name, String description, Instant datetimestart, Instant datetimeend, Integer hoursneededestimate, Job idjob, Location idlocation) {
        this.name = name;
        this.description = description;
        this.datetimestart = datetimestart;
        this.datetimeend = datetimeend;
        this.hoursneededestimate = hoursneededestimate;
        this.idjob = idjob;
        this.idlocation = idlocation;
    }
}

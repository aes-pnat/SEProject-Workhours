package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workhoursinput")
public class Workhoursinput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idworkhoursinput", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "workhoursdone", nullable = false)
    private Integer workhoursdone;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idemployee", nullable = false)
    private Employee idemployee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idtask", nullable = false)
    private Task idtask;

    public Task getIdtask() {
        return idtask;
    }

    public void setIdtask(Task idtask) {
        this.idtask = idtask;
    }

    public Employee getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(Employee idemployee) {
        this.idemployee = idemployee;
    }

    public Integer getWorkhoursdone() {
        return workhoursdone;
    }

    public void setWorkhoursdone(Integer workhoursdone) {
        this.workhoursdone = workhoursdone;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Workhoursinput() {

    }

    public Workhoursinput(Task task, LocalDate date, Integer hoursDone, Employee employee) {
        this.date = date;
        this.idtask = task;
        this.workhoursdone = hoursDone;
        this.idemployee = employee;
    }
}
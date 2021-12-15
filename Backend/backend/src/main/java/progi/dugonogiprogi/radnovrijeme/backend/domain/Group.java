package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.*;

@Entity
@Table(name = "\"group\"")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgroup", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idleader", nullable = false)
    private Employee idleader;

    @ManyToOne
    @JoinColumn(name = "idjob")
    private Job idjob;

    public Job getIdjob() {
        return idjob;
    }

    public void setIdjob(Job idjob) {
        this.idjob = idjob;
    }

    public Employee getIdleader() {
        return idleader;
    }

    public void setIdleader(Employee idleader) {
        this.idleader = idleader;
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
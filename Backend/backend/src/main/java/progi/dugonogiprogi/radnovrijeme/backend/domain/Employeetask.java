package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employeetask")
public class Employeetask {
    @EmbeddedId
    private EmployeetaskId id;

    @Column(name = "realized")
    private Integer realized;

    public Integer getRealized() {
        return realized;
    }

    public void setRealized(Integer realized) {
        this.realized = realized;
    }

    public EmployeetaskId getId() {
        return id;
    }

    public void setId(EmployeetaskId id) {
        this.id = id;
    }
}
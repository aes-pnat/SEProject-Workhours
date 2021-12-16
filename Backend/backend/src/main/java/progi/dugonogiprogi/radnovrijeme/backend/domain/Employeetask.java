package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "employeetask")
@Entity
public class Employeetask {
    @EmbeddedId
    private EmployeetaskId id;

    public EmployeetaskId getId() {
        return id;
    }

    public void setId(EmployeetaskId id) {
        this.id = id;
    }
}
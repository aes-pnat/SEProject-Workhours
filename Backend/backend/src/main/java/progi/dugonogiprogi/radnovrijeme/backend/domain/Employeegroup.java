package progi.dugonogiprogi.radnovrijeme.backend.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "employeegroup")
public class Employeegroup {
    @EmbeddedId
    private EmployeegroupId id;

    public EmployeegroupId getId() {
        return id;
    }

    public void setId(EmployeegroupId id) {
        this.id = id;
    }
}
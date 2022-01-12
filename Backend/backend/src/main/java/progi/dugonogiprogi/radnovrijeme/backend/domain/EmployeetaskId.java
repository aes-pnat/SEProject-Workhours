package progi.dugonogiprogi.radnovrijeme.backend.domain;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeetaskId implements Serializable {
    private static final long serialVersionUID = 7453698844066585433L;
    @Column(name = "idemployee", nullable = false, length = 11)
    private String idemployee;
    @Column(name = "idtask", nullable = false)
    private Integer idtask;

    public Integer getIdtask() {
        return idtask;
    }

    public void setIdtask(Integer idtask) {
        this.idtask = idtask;
    }

    public String getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(String idemployee) {
        this.idemployee = idemployee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idemployee, idtask);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeetaskId entity = (EmployeetaskId) o;
        return Objects.equals(this.idemployee, entity.idemployee) &&
                Objects.equals(this.idtask, entity.idtask);
    }
}
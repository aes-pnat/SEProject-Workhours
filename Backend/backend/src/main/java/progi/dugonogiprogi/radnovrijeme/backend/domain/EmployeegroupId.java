package progi.dugonogiprogi.radnovrijeme.backend.domain;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeegroupId implements Serializable {
    private static final long serialVersionUID = -7811514571471323L;
    @Column(name = "idemployee", nullable = false, length = 11)
    private String idemployee;
    @Column(name = "idgroup", nullable = false)
    private Integer idgroup;

    public Integer getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(Integer idgroup) {
        this.idgroup = idgroup;
    }

    public String getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(String idemployee) {
        this.idemployee = idemployee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idemployee, idgroup);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeegroupId entity = (EmployeegroupId) o;
        return Objects.equals(this.idemployee, entity.idemployee) &&
                Objects.equals(this.idgroup, entity.idgroup);
    }
}
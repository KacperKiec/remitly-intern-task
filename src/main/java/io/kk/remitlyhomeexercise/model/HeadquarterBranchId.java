package io.kk.remitlyhomeexercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class HeadquarterBranchId implements java.io.Serializable {
    private static final long serialVersionUID = -1275621202531902607L;
    @Column(name = "headquarter_id", nullable = false)
    private Integer headquarterId;

    @Column(name = "branch_id", nullable = false)
    private Integer branchId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HeadquarterBranchId entity = (HeadquarterBranchId) o;
        return Objects.equals(this.branchId, entity.branchId) &&
                Objects.equals(this.headquarterId, entity.headquarterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, headquarterId);
    }

}
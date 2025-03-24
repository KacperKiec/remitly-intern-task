package io.kk.remitlyhomeexercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "headquarter_branch")
public class HeadquarterBranch {
    @EmbeddedId
    private HeadquarterBranchId id;

    @MapsId("headquarterId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "headquarter_id", nullable = false)
    private Bank headquarter;

    @MapsId("branchId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "branch_id", nullable = false)
    private Bank branch;

    public HeadquarterBranch() {}

    public HeadquarterBranch(Bank headquarter, Bank branch) {
        this.headquarter = headquarter;
        this.branch = branch;
        HeadquarterBranchId id = new HeadquarterBranchId();
        id.setHeadquarterId(headquarter.getId());
        id.setBranchId(branch.getId());
        this.id = id;
    }
}
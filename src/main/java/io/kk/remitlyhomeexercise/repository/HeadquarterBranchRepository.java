package io.kk.remitlyhomeexercise.repository;

import io.kk.remitlyhomeexercise.model.HeadquarterBranch;

import java.util.List;

public interface HeadquarterBranchRepository {
    HeadquarterBranch save(HeadquarterBranch headquarterBranch);
    List<HeadquarterBranch> findByHeadquarter_Id(Integer headquarterId);
}

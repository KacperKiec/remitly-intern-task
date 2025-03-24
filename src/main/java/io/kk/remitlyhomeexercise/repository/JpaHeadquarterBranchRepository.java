package io.kk.remitlyhomeexercise.repository;

import io.kk.remitlyhomeexercise.model.HeadquarterBranch;
import io.kk.remitlyhomeexercise.model.HeadquarterBranchId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaHeadquarterBranchRepository extends JpaRepository<HeadquarterBranch, HeadquarterBranchId>, HeadquarterBranchRepository {

}

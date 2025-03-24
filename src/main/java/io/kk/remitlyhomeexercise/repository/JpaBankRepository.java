package io.kk.remitlyhomeexercise.repository;

import io.kk.remitlyhomeexercise.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaBankRepository extends JpaRepository<Bank, Integer>, BankRepository {

}

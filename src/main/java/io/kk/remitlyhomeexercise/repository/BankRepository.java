package io.kk.remitlyhomeexercise.repository;

import io.kk.remitlyhomeexercise.model.Bank;

import java.util.List;
import java.util.Optional;

public interface BankRepository {
    Bank save(Bank bank);
    void deleteBySwift(String swift);
    Optional<Bank> findBySwift(String swift);
    Optional<Bank> findById(Integer id);
    List<Bank> findByIso2(String iso2);
    List<Bank> findAll();
    List<Bank> findBySwiftStartsWith(String swiftPrefix);
    long count();
}

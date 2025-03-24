package io.kk.remitlyhomeexercise.service;

import io.kk.remitlyhomeexercise.dto.*;
import io.kk.remitlyhomeexercise.exception.BankAlreadyExistsException;
import io.kk.remitlyhomeexercise.exception.BankNotFoundException;
import io.kk.remitlyhomeexercise.exception.SwiftCodeException;
import io.kk.remitlyhomeexercise.model.Bank;
import io.kk.remitlyhomeexercise.model.HeadquarterBranch;
import io.kk.remitlyhomeexercise.repository.BankRepository;
import io.kk.remitlyhomeexercise.repository.HeadquarterBranchRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    private final HeadquarterBranchRepository headquarterBranchRepository;
    private final Mapper mapper;

    public HeadquarterDTO getBanksBySwift(String swift) {
        var existingBank = bankRepository.findBySwift(swift).orElseThrow(
                () -> new BankNotFoundException("Bank with given swift code was not found")
        );

        if (existingBank.getIsHeadquarter()) {
            var branches = headquarterBranchRepository.findByHeadquarter_Id(existingBank.getId())
                    .stream().map(HeadquarterBranch::getBranch).toList();
            return mapper.toHeadquarterDTO(existingBank, branches);
        }
        return mapper.toHeadquarterDTO(existingBank, null);
    }

    public CountryDTO getBanksByISO2(String iso2) {
        var banks = bankRepository.findByIso2(iso2);
        var bank = banks.getFirst();
        return mapper.toCountryDTO(bank.getIso2(), bank.getCountry(), banks);
    }

    @Transactional
    public String addBank(BankDTO bankDTO) {
        var existingBank = bankRepository.findBySwift(bankDTO.getSwiftCode());
        if (existingBank.isPresent()) {
            throw new BankAlreadyExistsException("Bank with given parameters already exists");
        }

        Bank bank = new Bank();
        bank.setName(bankDTO.getBankName());
        bank.setAddress(bankDTO.getAddress());
        bank.setIso2(bankDTO.getCountryISO2());
        bank.setCountry(bankDTO.getCountryName());
        String swiftCode = bankDTO.getSwiftCode().toUpperCase();

        if (swiftCode.length() != 11 || (bankDTO.getIsHeadquarter() && !swiftCode.endsWith("XXX"))) {
            throw new SwiftCodeException("Swift code is invalid or incompatible with other parameters");
        }

        bank.setSwift(swiftCode);

        if(bank.getSwift().endsWith("XXX")) {
            bank.setIsHeadquarter(true);
            bankRepository.save(bank);
            List<Bank> banks = bankRepository.findBySwiftStartsWith(bank.getSwift().substring(0, bank.getSwift().length() - 3));
            for(Bank b : banks) {
                HeadquarterBranch headquarterBranch = new HeadquarterBranch(bank, b);
                headquarterBranchRepository.save(headquarterBranch);
            }
        } else {
            bank.setIsHeadquarter(false);
            bankRepository.save(bank);
            String headquarterSwiftCode = bank.getSwift().substring(0, bank.getSwift().length() - 3) + "XXX";
            var headquarter = bankRepository.findBySwift(headquarterSwiftCode);
            if (headquarter.isPresent()) {
                HeadquarterBranch headquarterBranch = new HeadquarterBranch(headquarter.get(), bank);
                headquarterBranchRepository.save(headquarterBranch);
            }
        }

        return "Bank added successfully";
    }

    @Transactional
    public String deleteBank(String swiftCode) {
        var existingBank = bankRepository.findBySwift(swiftCode).orElseThrow(
                () -> new BankNotFoundException("Bank with given swift code was not found")
        );
        bankRepository.deleteBySwift(swiftCode);
        return "Deleted Bank with given swift code: " + swiftCode;
    }
}

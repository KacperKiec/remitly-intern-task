package io.kk.remitlyhomeexercise.dto;

import io.kk.remitlyhomeexercise.model.Bank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public HeadquarterDTO toHeadquarterDTO(Bank bank, List<Bank> branches) {
        HeadquarterDTO headquarterDTO = new HeadquarterDTO();
        headquarterDTO.setAddress(bank.getAddress());
        headquarterDTO.setBankName(bank.getName());
        headquarterDTO.setCountryISO2(bank.getIso2());
        headquarterDTO.setCountryName(bank.getCountry());
        headquarterDTO.setIsHeadquarter(bank.getIsHeadquarter());
        headquarterDTO.setSwiftCode(bank.getSwift());
        if (branches != null) {
            //headquarterDTO.setBranches(new ArrayList<>());
            headquarterDTO.setBranches(branches.stream().map(this::toBranchDTO).toList());
        }
        return headquarterDTO;
    }

    public BranchDTO toBranchDTO(Bank bank) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setAddress(bank.getAddress());
        branchDTO.setBankName(bank.getName());
        branchDTO.setCountryISO2(bank.getIso2());
        branchDTO.setIsHeadquarter(bank.getIsHeadquarter());
        branchDTO.setSwiftCode(bank.getSwift());
        return branchDTO;
    }

    public CountryDTO toCountryDTO(String iso2, String countryName, List<Bank> banks) {
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCountryISO2(iso2);
        countryDTO.setCountryName(countryName);
        if (banks != null) {
            countryDTO.setSwiftCodes(banks.stream().map(this::toBranchDTO).toList());
        }
        return countryDTO;
    }
}

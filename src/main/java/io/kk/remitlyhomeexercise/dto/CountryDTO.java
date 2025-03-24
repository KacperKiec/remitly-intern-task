package io.kk.remitlyhomeexercise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CountryDTO {
    private String countryISO2;
    private String countryName;
    private List<BranchDTO> swiftCodes = new ArrayList<>();
}

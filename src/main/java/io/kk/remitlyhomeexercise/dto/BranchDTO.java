package io.kk.remitlyhomeexercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchDTO {
    private String address;
    private String bankName;
    private String countryISO2;
    private Boolean isHeadquarter;
    private String swiftCode;
}

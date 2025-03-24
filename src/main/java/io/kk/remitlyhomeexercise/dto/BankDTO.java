package io.kk.remitlyhomeexercise.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDTO {
    @NotNull
    private String address;
    @NotNull
    private String bankName;
    @NotNull
    private String countryISO2;
    @NotNull
    private String countryName;
    @NotNull
    private Boolean isHeadquarter;
    @NotNull
    private String swiftCode;
}

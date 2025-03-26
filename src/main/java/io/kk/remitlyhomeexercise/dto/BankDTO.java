package io.kk.remitlyhomeexercise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BankDTO {

    @NotBlank(message = "Bank name can not be empty")
    private String bankName;

    @NotBlank(message = "Address can not be empty")
    private String address;

    @NotBlank(message = "ISO2 Code is required")
    @Size(min = 2, max = 2, message = "ISO@ Code must be exactly 2 signs long")
    private String countryISO2;

    @NotBlank(message = "Country name is required")
    private String countryName;

    @NotBlank(message = "Swift Code is required and must have exactly 11 signs long")
    private String swiftCode;

    @NotNull(message = "Headquarter status is required")
    private boolean isHeadquarter;
}


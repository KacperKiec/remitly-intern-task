package io.kk.remitlyhomeexercise.controller;

import io.kk.remitlyhomeexercise.dto.BankDTO;
import io.kk.remitlyhomeexercise.dto.CountryDTO;
import io.kk.remitlyhomeexercise.dto.HeadquarterDTO;
import io.kk.remitlyhomeexercise.dto.RequestResponse;
import io.kk.remitlyhomeexercise.service.BankService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/v1/swift-codes")
@AllArgsConstructor
public class BankController {

    private final BankService bankService;

    @GetMapping(value = "/{swift-code}")
    ResponseEntity<HeadquarterDTO> getHeadquarter(@PathVariable("swift-code") String swiftCode) {
        var result = bankService.getBanksBySwift(swiftCode);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/country/{countryISO2code}")
    ResponseEntity<CountryDTO> getCountry(@PathVariable("countryISO2code") String countryISO2) {
        var result = bankService.getBanksByISO2(countryISO2);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    ResponseEntity<RequestResponse> addBank(@RequestBody @Valid BankDTO bankDTO) {
        var result = bankService.addBank(bankDTO);
        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setMessage(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestResponse);
    }

    @DeleteMapping(value = "/{swift-code}")
    ResponseEntity<RequestResponse> deleteBank(@PathVariable("swift-code") String swiftCode) {
        var result = bankService.deleteBank(swiftCode);
        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setMessage(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestResponse);
    }
}

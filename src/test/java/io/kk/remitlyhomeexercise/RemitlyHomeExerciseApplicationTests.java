package io.kk.remitlyhomeexercise;

import io.kk.remitlyhomeexercise.dto.BankDTO;
import io.kk.remitlyhomeexercise.exception.BankAlreadyExistsException;
import io.kk.remitlyhomeexercise.model.Bank;
import io.kk.remitlyhomeexercise.repository.BankRepository;
import io.kk.remitlyhomeexercise.repository.HeadquarterBranchRepository;
import io.kk.remitlyhomeexercise.service.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RemitlyHomeExerciseApplicationTests {

    @Mock
    private BankRepository bankRepository;

    @Mock
    private HeadquarterBranchRepository headquarterBranchRepository;

    @InjectMocks
    private BankService bankService;

    private BankDTO bankDTO;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bankDTO = new BankDTO("New Bank", "Address 123", "PL", "Poland", "NEWBANKKXXX", true);
        bank = new Bank();
        bank.setName(bankDTO.getBankName());
        bank.setAddress(bankDTO.getAddress());
        bank.setIso2(bankDTO.getCountryISO2());
        bank.setCountry(bankDTO.getCountryName());
        bank.setSwift(bankDTO.getSwiftCode());
        bank.setIsHeadquarter(bankDTO.isHeadquarter());
    }

    @Test
    void shouldAddBankSuccessfully() {
        when(bankRepository.findBySwift(bankDTO.getSwiftCode())).thenReturn(Optional.empty());
        when(bankRepository.save(any(Bank.class))).thenReturn(bank);

        String result = bankService.addBank(bankDTO);

        assertEquals("Bank added successfully", result);
        verify(bankRepository, times(1)).save(any(Bank.class));
    }

    @Test
    void shouldThrowExceptionWhenBankAlreadyExists() {
        when(bankRepository.findBySwift(bankDTO.getSwiftCode())).thenReturn(Optional.of(bank));

        assertThrows(BankAlreadyExistsException.class, () -> bankService.addBank(bankDTO));
        verify(bankRepository, never()).save(any(Bank.class));
    }

    @Test
    void shouldDeleteBankSuccessfully() {
        when(bankRepository.findBySwift(bankDTO.getSwiftCode())).thenReturn(Optional.of(bank));

        String result = bankService.deleteBank(bankDTO.getSwiftCode());

        assertEquals("Deleted Bank with given swift code: " + bankDTO.getSwiftCode(), result);
        verify(bankRepository, times(1)).deleteBySwift(bank.getSwift());
    }

}

package io.kk.remitlyhomeexercise.service;

import io.kk.remitlyhomeexercise.model.Bank;
import io.kk.remitlyhomeexercise.model.HeadquarterBranch;
import io.kk.remitlyhomeexercise.repository.BankRepository;
import io.kk.remitlyhomeexercise.repository.HeadquarterBranchRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final BankRepository bankRepository;
    private final HeadquarterBranchRepository headquarterBranchRepository;
    private final String filePath;

    public DataLoader(BankRepository bankRepository, HeadquarterBranchRepository headquarterBranchRepository, @Value("${app.file-path}") String filePath) {
        this.bankRepository = bankRepository;
        this.headquarterBranchRepository = headquarterBranchRepository;
        this.filePath = filePath;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (bankRepository.count() > 0) {
            System.out.println("Data was imported previously. Skipping import.");
            return;
        }

        ClassPathResource resource = new ClassPathResource(filePath);
        FileInputStream fileInputStream = new FileInputStream(resource.getFile());
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);


        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;

            Bank bank = new Bank();
            bank.setIso2(row.getCell(0).getStringCellValue().toUpperCase());
            bank.setSwift(row.getCell(1).getStringCellValue().toUpperCase());
            bank.setCodeType(row.getCell(2).getStringCellValue().toUpperCase());
            bank.setName(row.getCell(3).getStringCellValue().toUpperCase());
            bank.setAddress(row.getCell(4).getStringCellValue().toUpperCase());
            bank.setCity(row.getCell(5).getStringCellValue().toUpperCase());
            bank.setCountry(row.getCell(6).getStringCellValue().toUpperCase());
            bank.setTimezone(row.getCell(7).getStringCellValue().toUpperCase());

            bank.setIsHeadquarter(bank.getSwift().endsWith("XXX"));

            bankRepository.save(bank);
        }

        List<Bank> banks = bankRepository.findAll();

        for (Bank bank : banks) {
            if (!bank.getIsHeadquarter()) {
                var existingHeadquarter = bankRepository.findBySwift(bank.getSwift().substring(0, bank.getSwift().length() - 3) + "XXX");
                if (existingHeadquarter.isPresent()) {
                    HeadquarterBranch headquarterBranch = new HeadquarterBranch(existingHeadquarter.get(), bank);
                    headquarterBranchRepository.save(headquarterBranch);
                }
            }
        }

        workbook.close();
        fileInputStream.close();
        System.out.println("Import ended successfully!");
    }
}

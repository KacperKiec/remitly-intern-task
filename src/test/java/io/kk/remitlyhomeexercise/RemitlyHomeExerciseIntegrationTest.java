package io.kk.remitlyhomeexercise;

import io.kk.remitlyhomeexercise.repository.BankRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RemitlyHomeExerciseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BankRepository bankRepository;

    @Test
    void shouldAddBankAndRetrieveIt() throws Exception {
        String bankJson = """
            {
                "bankName": "New Bank",
                "address": "Address 123",
                "countryISO2": "PL",
                "countryName": "Poland",
                "swiftCode": "NEWBANKKXXX",
                "isHeadquarter": true
            }
        """;

        mockMvc.perform(post("/v1/swift-codes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bankJson))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/v1/swift-codes/NEWBANKKXXX"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bankName").value("New Bank"));
    }

    @Test
    void shouldReturnNotFoundForNonExistingBank() throws Exception {
        mockMvc.perform(get("/v1/swift-codes/NONEXISTENT"))
                .andExpect(status().isNotFound());
    }
}

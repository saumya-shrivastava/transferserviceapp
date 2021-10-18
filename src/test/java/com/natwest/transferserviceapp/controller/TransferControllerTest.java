package com.natwest.transferserviceapp.controller;

import com.natwest.transferserviceapp.model.Status;
import com.natwest.transferserviceapp.service.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransferController.class)
class TransferControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransferService transferService;

    @Test
    void givenMissingInput_whenMakeTransfer_thenBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/natwest/transfers/initiate")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenInvalidInput_whenMakeTransfer_thenBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/natwest/transfers/initiate")

                .content("{\n" +
                        "    \"sourceAccountNumber\": 111111,\n" +
                        "    \"destAccountNumber\": 7,\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void givenRequestisCorrect_whenTransferAmount_thenOk() throws Exception {
        given(transferService.transferAmount(anyObject())).willReturn(
                Status.SUCESS);
        mvc.perform(MockMvcRequestBuilders.post("/natwest/transfers/initiate")
                .content("{\n" +
                        "    \"sourceAccountNumber\": 111111,\n" +
                        "    \"destAccountNumber\": 222222,\n" +
                        "    \"amount\": 50\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
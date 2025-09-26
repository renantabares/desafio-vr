package br.com.vr.controllerTests;


import br.com.vr.controller.TransactionController;
import br.com.vr.dtos.TransactionDTO;
import br.com.vr.exceptions.CardTransactionException;
import br.com.vr.services.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(TransactionController.class)
    class TransactionControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private TransactionService transactionService;

        @Test
        void shouldReturn201WhenTransactionSuccess() throws Exception {
            TransactionDTO dto = new TransactionDTO("123456789", "1234", BigDecimal.TEN);

            when(transactionService.transaction(dto)).thenReturn(true);

            mockMvc.perform(post("/transacoes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isCreated())
                    .andExpect(content().string("OK"));
        }

        @Test
        void shouldReturn400WhenTransactionFails() throws Exception {
            TransactionDTO dto = new TransactionDTO("123456789", "wrong", BigDecimal.TEN);

            when(transactionService.transaction(dto))
                    .thenThrow(new CardTransactionException("SENHA_INVALIDA"));

            mockMvc.perform(post("/transacoes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dto)))
                    .andExpect(status().isBadRequest());
        }

}

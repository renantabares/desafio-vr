package br.com.vr.controllerTests;

import br.com.vr.controller.CardController;
import br.com.vr.dtos.CardDTO;
import br.com.vr.exceptions.CardNotFoundException;
import br.com.vr.models.Card;
import br.com.vr.services.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
class CardControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnBalance() throws Exception {
        String cardNumber = "123456789";
        when(cardService.getBalance(cardNumber)).thenReturn(BigDecimal.valueOf(500));

        mockMvc.perform(get("/cartoes/{cardNumber}", cardNumber))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("500"));
    }

    @Test
    void shouldCreateCard() throws Exception {
        CardDTO dto = new CardDTO("123456789", "senha123", null);
        Card card = Card.builder()
                .numberCard(dto.numeroCartao())
                .HashPassword("hash-simulado")
                .cardBalance(BigDecimal.valueOf(500))
                .build();

        when(cardService.createCard(any(CardDTO.class))).thenReturn(card);

        mockMvc.perform(post("/cartoes")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.numberCard").value("123456789"))
                .andExpect((ResultMatcher) jsonPath("$.cardBalance").value(500));
    }

    @Test
    void shouldReturn404IfCardNotFound() throws Exception {
        String cardNumber = "0000";
        when(cardService.getBalance(cardNumber)).thenThrow(new CardNotFoundException("Cartão não encontrado: " + cardNumber));

        mockMvc.perform(get("/cartoes/{cardNumber}", cardNumber))
                .andExpect(status().isNotFound());
    }
}

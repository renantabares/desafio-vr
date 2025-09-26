package br.com.vr.servicesTests;

import br.com.vr.dtos.TransactionDTO;
import br.com.vr.exceptions.CardTransactionException;
import br.com.vr.models.Card;
import br.com.vr.repositories.CardRepository;
import br.com.vr.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private TransactionService transactionService;

    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    void shouldThrowWhenCardNotFound() {
        TransactionDTO dto = new TransactionDTO("123", "senha", BigDecimal.TEN);

        when(cardRepository.findByNumberCard("123")).thenReturn(Optional.empty());

        CardTransactionException ex = assertThrows(CardTransactionException.class,
                () -> transactionService.transaction(dto));

        assertEquals("CARTAO_INEXISTENTE", ex.getMessage());
    }

    @Test
    void shouldThrowWhenPasswordInvalid() {
        String encodedPass = encoder.encode("senhaCorreta");

        Card card = new Card();
        card.setNumberCard("123");
        card.setHashPassword(encodedPass);
        card.setCardBalance(BigDecimal.valueOf(100));

        TransactionDTO dto = new TransactionDTO("123", "senhaErrada", BigDecimal.TEN);

        when(cardRepository.findByNumberCard("123")).thenReturn(Optional.of(card));

        CardTransactionException ex = assertThrows(CardTransactionException.class,
                () -> transactionService.transaction(dto));

        assertEquals("SENHA_INVALIDA", ex.getMessage());
    }

    @Test
    void shouldThrowWhenInsufficientBalance() {
        String encodedPass = encoder.encode("1234");

        Card card = new Card();
        card.setNumberCard("123");
        card.setHashPassword(encodedPass);
        card.setCardBalance(BigDecimal.valueOf(5));

        TransactionDTO dto = new TransactionDTO("123", "1234", BigDecimal.TEN);

        when(cardRepository.findByNumberCard("123")).thenReturn(Optional.of(card));

        CardTransactionException ex = assertThrows(CardTransactionException.class,
                () -> transactionService.transaction(dto));

        assertEquals("SALDO_INSUFICIENTE", ex.getMessage());
    }

    @Test
    void shouldProcessTransactionSuccessfully() {
        String encodedPass = encoder.encode("1234");

        Card card = new Card();
        card.setNumberCard("123");
        card.setHashPassword(encodedPass);
        card.setCardBalance(BigDecimal.valueOf(100));

        TransactionDTO dto = new TransactionDTO("123", "1234", BigDecimal.TEN);

        when(cardRepository.findByNumberCard("123")).thenReturn(Optional.of(card));

        boolean result = transactionService.transaction(dto);

        assertTrue(result);
        assertEquals(BigDecimal.valueOf(90), card.getCardBalance());
    }
}

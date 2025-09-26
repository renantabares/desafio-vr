package br.com.vr.servicesTests;

import br.com.vr.dtos.CardDTO;
import br.com.vr.exceptions.CardNotFoundException;
import br.com.vr.models.Card;
import br.com.vr.repositories.CardRepository;
import br.com.vr.services.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CardServiceTest {
    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCardSuccessfully() {
        CardDTO dto = new CardDTO("123456789", "senha123", null);
        when(cardRepository.findByNumberCard(dto.numeroCartao())).thenReturn(Optional.empty());

        Card savedCard = Card.builder()
                .numberCard(dto.numeroCartao())
                .HashPassword(new BCryptPasswordEncoder().encode(dto.senha()))
                .cardBalance(BigDecimal.valueOf(500))
                .build();

        when(cardRepository.save(any(Card.class))).thenReturn(savedCard);

        Card result = cardService.createCard(dto);

        assertNotNull(result);
        assertEquals(dto.numeroCartao(), result.getNumberCard());
        assertEquals(BigDecimal.valueOf(500), result.getCardBalance());
        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    void shouldThrowExceptionIfCardExists() {
        CardDTO dto = new CardDTO("123456789", "senha123", null);
        Card existingCard = Card.builder().numberCard(dto.numeroCartao()).build();
        when(cardRepository.findByNumberCard(dto.numeroCartao())).thenReturn(Optional.of(existingCard));

        CardNotFoundException exception = assertThrows(CardNotFoundException.class,
                () -> cardService.createCard(dto));

        assertEquals("Cartão já existe com este número: " + dto.numeroCartao(), exception.getMessage());
        verify(cardRepository, never()).save(any());
    }

    @Test
    void shouldReturnAllCards() {
        Card card1 = Card.builder().numberCard("1111").build();
        Card card2 = Card.builder().numberCard("2222").build();
        when(cardRepository.findAll()).thenReturn(List.of(card1, card2));

        List<Card> result = cardService.findAll();

        assertEquals(2, result.size());
        verify(cardRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnBalanceForExistingCard() {
        String cardNumber = "123456789";
        Card card = Card.builder().numberCard(cardNumber).cardBalance(BigDecimal.valueOf(500)).build();
        when(cardRepository.findByNumberCard(cardNumber)).thenReturn(Optional.of(card));

        BigDecimal balance = cardService.getBalance(cardNumber);

        assertEquals(BigDecimal.valueOf(500), balance);
        verify(cardRepository, times(1)).findByNumberCard(cardNumber);
    }

    @Test
    void shouldThrowExceptionForNonExistingCardBalance() {
        String cardNumber = "0000";
        when(cardRepository.findByNumberCard(cardNumber)).thenReturn(Optional.empty());

        CardNotFoundException exception = assertThrows(CardNotFoundException.class,
                () -> cardService.getBalance(cardNumber));

        assertEquals("Cartão não encontrado: " + cardNumber, exception.getMessage());
        verify(cardRepository, times(1)).findByNumberCard(cardNumber);
    }
}

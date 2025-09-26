package br.com.vr.services;

import br.com.vr.dtos.CardDTO;
import br.com.vr.exceptions.CardNotFoundException;
import br.com.vr.models.Card;
import br.com.vr.repositories.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Card createCard(CardDTO request) {
        cardRepository.findByNumberCard(request.numeroCartao())
                .ifPresent(existingCard -> {
                    throw new CardNotFoundException(
                            "Cartão já existe com este número: " + request.numeroCartao()
                    );
                });
        Card card = Card.builder().numberCard(request.numeroCartao()).HashPassword((new BCryptPasswordEncoder()).encode(request.senha())).userId(null).cardBalance(BigDecimal.valueOf(500.00)).build();
        return  cardRepository.save(card);
    }

    public List<Card> findAll() {
        return cardRepository.findAll();
    }


    public BigDecimal getBalance(String cardNumber) {
        return cardRepository.findByNumberCard(cardNumber)
                .map(Card::getCardBalance)
                .orElseThrow(() -> new CardNotFoundException(
                        "Cartão não encontrado: " + cardNumber
                ));
    }
}

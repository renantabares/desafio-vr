package br.com.vr.services;

import br.com.vr.dtos.TransactionDTO;
import br.com.vr.exceptions.CardNotFoundException;
import br.com.vr.exceptions.CardTransactionException;
import br.com.vr.models.Card;
import br.com.vr.repositories.CardRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final CardRepository cardRepository;

    public boolean transaction  (TransactionDTO request) {

        Card card = cardRepository.findByNumberCard(request.numeroCartao())
                .orElseThrow(() -> new CardTransactionException("CARTAO_INEXISTENTE"));

        Optional.of(card)
                .filter(c -> new BCryptPasswordEncoder().matches(request.senha(), c.getHashPassword()))
                .orElseThrow(() -> new CardTransactionException("SENHA_INVALIDA"));

        Optional.of(card)
                .filter(c -> c.getCardBalance().compareTo(request.valor()) >= 0)
                .orElseThrow(() -> new CardTransactionException("SALDO_INSUFICIENTE"))
                .setCardBalance(card.getCardBalance().subtract(request.valor()));

        return true;


    }
}

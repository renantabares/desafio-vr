package br.com.vr.repositories;

import br.com.vr.models.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CardRepository extends MongoRepository<Card, String> {
    Optional<Card> findByNumberCard(String numberCard);

    boolean existsByNumberCard(String numberCard);
}

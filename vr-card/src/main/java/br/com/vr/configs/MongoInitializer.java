package br.com.vr.configs;

import br.com.vr.models.Card;
import br.com.vr.repositories.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MongoInitializer implements CommandLineRunner {
    private final CardRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            repository.save(Card.builder()
                    .id(null)
                    .numberCard("123456789")
                    .HashPassword(new BCryptPasswordEncoder().encode("123456"))
                    .userId(null)
                    .build());
            repository.save(Card.builder()
                    .id(null)
                    .numberCard("987654321")
                    .HashPassword(new BCryptPasswordEncoder().encode("654321"))
                    .userId(null)
                    .build());
        } else {
            System.out.println("⚠️ Dados já existentes, não inicializado novamente.");
        }
    }
}

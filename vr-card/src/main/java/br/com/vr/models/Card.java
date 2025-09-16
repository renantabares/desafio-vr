package br.com.vr.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "cards")
@Getter
@Setter
@Builder
public class Card {
    @Id
    private String id;
    @Indexed(unique = true)
    private String numberCard;
    private String HashPassword;
    private String userId;
    private Instant createdAt;
}

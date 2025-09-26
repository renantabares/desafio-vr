package br.com.vr.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "cards")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    private String id;
    @Indexed(unique = true)
    private String numberCard;
    private String HashPassword;
    private String userId;
    private BigDecimal cardBalance;
    private Instant createdAt;
}

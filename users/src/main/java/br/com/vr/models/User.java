package br.com.vr.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.smartcardio.Card;
import java.util.List;
import java.util.Set;

@Document(collection = "users")
@Getter
@Setter
@Builder
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    @DBRef
    private List<Role> roles;
}
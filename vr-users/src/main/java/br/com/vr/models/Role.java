package br.com.vr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.smartcardio.Card;
import java.util.List;

@Document(collection = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    private String id;
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

}

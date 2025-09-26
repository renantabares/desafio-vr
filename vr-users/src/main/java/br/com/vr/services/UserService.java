package br.com.vr.services;

import br.com.vr.models.User;
import br.com.vr.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User getByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + username));
    }

    public String encryptPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }


}

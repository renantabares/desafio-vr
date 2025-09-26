package br.com.vr.services;

import br.com.vr.clients.UsersClient;
import br.com.vr.dtos.LoginRequest;
import br.com.vr.dtos.LoginResponse;
import br.com.vr.dtos.RoleDTO;
import br.com.vr.dtos.UserDTO;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoginService {
    private final UsersClient usersClient;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        UserDTO user = usersClient.getUser(loginRequest.userName()).getBody();

        if (Objects.isNull(user) || passwordEncoder.matches(loginRequest.password(), user.password())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        var now = Instant.now();
        var expiresIn = 1200L;
        var scopes = user.roles().stream().map(RoleDTO::roleName).collect(Collectors.joining( " "));
        var claims = JwtClaimsSet.builder()
                .issuer("http://localhost:8086")
                .subject(user.id())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now)
                .claim("scope" , scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresIn);
    }
}

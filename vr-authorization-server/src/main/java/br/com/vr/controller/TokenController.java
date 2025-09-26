package br.com.vr.controller;

import br.com.vr.dtos.LoginRequest;
import br.com.vr.dtos.LoginResponse;
import br.com.vr.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping(value = "/login")
public class TokenController {
    @Autowired
    private LoginService service;
    private final JwtEncoder jwtEncoder;

    public TokenController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(service.login(request), HttpStatus.OK);
    }

}

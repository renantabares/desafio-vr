package br.com.vr.controller;

import br.com.vr.dtos.UserDTO;
import br.com.vr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/oauth")
public class OAuthController {
    @Autowired
    UserService userService;
    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDTO> getUser (@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(username));
    }
}

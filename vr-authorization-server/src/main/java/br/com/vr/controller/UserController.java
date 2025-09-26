package br.com.vr.controller;

import br.com.vr.clients.UsersClient;
import br.com.vr.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UserController {

    UsersClient usersClient;
    @GetMapping
    public ResponseEntity<UserDTO> getUser(String username) {
        return usersClient.getUser(username);
    }
}

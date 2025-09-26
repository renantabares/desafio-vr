package br.com.vr.controller;

import br.com.vr.services.UserService;
import br.com.vr.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    UserService service;
    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = service.getByUserName(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

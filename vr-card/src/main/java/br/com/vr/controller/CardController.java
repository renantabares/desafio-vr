package br.com.vr.controller;

import br.com.vr.dtos.CardDTO;
import br.com.vr.models.Card;
import br.com.vr.services.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
public class CardController {
    @Autowired
    CardService service;
    @GetMapping(value = "/{cardNumber}")
    public ResponseEntity<Double> getBalance (@PathVariable String cardNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getBalance(cardNumber));
    }
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody @Valid CardDTO request) {
        Card saved = this.service.createCard(request);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }


}

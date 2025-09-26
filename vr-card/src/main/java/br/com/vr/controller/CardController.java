package br.com.vr.controller;

import br.com.vr.dtos.CardDTO;
import br.com.vr.models.Card;
import br.com.vr.services.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cartoes")
@Tag(name = "Cartões", description = "Endpoints para gerenciamento de cartões")
public class CardController {
    @Autowired
    CardService service;
    @GetMapping(value = "/{cardNumber}")
    @Operation(summary = "Consulta saldo do cartão", description = "Retorna o saldo do cartão pelo número")
    public ResponseEntity<BigDecimal> getBalance (@PathVariable String cardNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getBalance(cardNumber));
    }
    @PostMapping
    @Operation(summary = "Cria um novo cartão", description = "Cria um cartão com número e senha fornecidos")
    public ResponseEntity<Card> createCard(@RequestBody @Valid CardDTO request) {
        Card saved = this.service.createCard(request);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }


}

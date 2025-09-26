package br.com.vr.controller;

import br.com.vr.dtos.CardDTO;
import br.com.vr.dtos.TransactionDTO;
import br.com.vr.models.Card;
import br.com.vr.services.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/transacoes")
@Tag(name = "Transações", description = "Endpoints para gerenciamento  das transações dos cartões VR")
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity transaction(@RequestBody @Valid TransactionDTO request) {

        return service.transaction(request)
                ? ResponseEntity.status(201).body("OK")
                : ResponseEntity.status(400).body("Falha na transação");

    }
}

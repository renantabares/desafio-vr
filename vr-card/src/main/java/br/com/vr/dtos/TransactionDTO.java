package br.com.vr.dtos;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record TransactionDTO(String numeroCartao, String senha, BigDecimal valor) {
}

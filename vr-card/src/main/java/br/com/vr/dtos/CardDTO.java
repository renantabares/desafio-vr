package br.com.vr.dtos;


import jakarta.validation.constraints.NotBlank;

public record CardDTO(@NotBlank String numeroCartao, @NotBlank String senha, Double saldo) {
}

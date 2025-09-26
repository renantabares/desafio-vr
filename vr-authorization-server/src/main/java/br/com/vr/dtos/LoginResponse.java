package br.com.vr.dtos;

public record LoginResponse(String accessToken, Long expiresIn) {
}

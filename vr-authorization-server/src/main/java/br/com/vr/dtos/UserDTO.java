package br.com.vr.dtos;

import java.util.List;

public record UserDTO(
        String id,
        String username,
        String password,
        List<RoleDTO> roles
) {}

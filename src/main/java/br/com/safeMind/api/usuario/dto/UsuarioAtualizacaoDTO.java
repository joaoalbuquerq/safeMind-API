package br.com.safeMind.api.usuario.dto;

import jakarta.validation.constraints.Email;

public record UsuarioAtualizacaoDTO(
        String nome,
        @Email
        String email
) {
}

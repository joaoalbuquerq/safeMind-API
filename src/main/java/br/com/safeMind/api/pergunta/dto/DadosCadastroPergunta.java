package br.com.safeMind.api.pergunta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosCadastroPergunta(
        @NotBlank
        String descricao,
        @NotNull
        UUID testeId
) {
}

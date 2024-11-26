package br.com.safeMind.api.teste.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTesteDTO(
        @NotBlank
        String nome,
        String descricao
){
}

package br.com.safeMind.api.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UsuarioCadastroDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2})|(\\d{11})$", message = "CPF inválido")
        String cpf,
        @Email(message = "O email é obrigatório e deve ser válido")
        String email,
        @Past
        LocalDate dataNascimento
) {

}

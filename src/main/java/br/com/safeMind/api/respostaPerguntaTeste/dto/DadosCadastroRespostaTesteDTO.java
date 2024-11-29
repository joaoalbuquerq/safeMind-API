package br.com.safeMind.api.respostaPerguntaTeste.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record DadosCadastroRespostaTesteDTO(
        @NotNull
        UUID testeId,

        @NotNull
        UUID usuarioId,

        @NotNull
        List<PerguntaRespostaDTO> perguntaResposta
) {

    public static class PerguntaRespostaDTO {
        private UUID perguntaId;
        private UUID respostaId;

        public UUID getPerguntaId() {
            return perguntaId;
        }

        public UUID getRespostaId() {
            return respostaId;
        }

        // Setters, caso queira manipular diretamente
        public void setPerguntaId(UUID perguntaId) {
            this.perguntaId = perguntaId;
        }

        public void setRespostaId(UUID respostaId) {
            this.respostaId = respostaId;
        }
    }

}

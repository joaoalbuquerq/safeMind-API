package br.com.safeMind.api.respostaPerguntaTeste.model;

import br.com.safeMind.api.pergunta.model.Pergunta;
import br.com.safeMind.api.resposta.model.Resposta;
import br.com.safeMind.api.teste.model.Teste;
import br.com.safeMind.api.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class RespostaPerguntaTeste {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "teste_id", referencedColumnName = "id")
    @JsonIgnore
    private Teste teste;

    @ManyToOne
    @JoinColumn(name = "pergunta_id", referencedColumnName = "id")
    @JsonIgnore
    private Pergunta pergunta;

    @ManyToOne
    @JoinColumn(name = "resposta_id", referencedColumnName = "id")
    @JsonIgnore
    private Resposta resposta;

    private LocalDateTime dataResposta;

    public RespostaPerguntaTeste(Teste teste, Usuario usuario, Pergunta pergunta, Resposta resposta) {
        this.usuario = usuario;
        this.teste = teste;
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.dataResposta = LocalDateTime.now();
    }
}

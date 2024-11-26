package br.com.safeMind.api.pergunta.model;

import br.com.safeMind.api.pergunta.dto.DadosCadastroPergunta;
import br.com.safeMind.api.teste.model.Teste;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "teste_id", nullable = false)
    @JsonIgnore
    private Teste teste;

    private String descricao;
    private LocalDate dataCriacao;
    private LocalDateTime ultimaAlteracao;

    public Pergunta() {
        super();
    }

    public Pergunta(DadosCadastroPergunta dto, Teste teste) {
        this.descricao= dto.descricao();
        this.teste = teste;
        this.dataCriacao = LocalDate.now();
        this.ultimaAlteracao = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(LocalDateTime ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
}

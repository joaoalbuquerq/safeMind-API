package br.com.safeMind.api.teste.model;

import br.com.safeMind.api.pergunta.model.Pergunta;
import br.com.safeMind.api.teste.dto.DadosCadastroTesteDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Teste {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "teste", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pergunta> perguntas;
    private LocalDate dataCriacao;
    private LocalDateTime ultimaAlteracao;

    public Teste(){
        super();
    }

    public Teste(DadosCadastroTesteDTO dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.dataCriacao = LocalDate.now();
        this.ultimaAlteracao = LocalDateTime.now();
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }
    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public void adicionarPergunta(Pergunta pergunta) {
        this.perguntas.add(pergunta);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

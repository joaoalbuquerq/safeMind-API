package br.com.safeMind.api.usuario.model;

import br.com.safeMind.api.usuario.dto.UsuarioAtualizacaoDTO;
import br.com.safeMind.api.usuario.dto.UsuarioCadastroDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAlteracao;

    private String login;
    private String senha;

    public Usuario(){
        super();
    }

    public Usuario(UsuarioCadastroDTO dto){
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.dataNascimento = dto.dataNascimento();
        this.dataCriacao = LocalDateTime.now();
        this.ultimaAlteracao = LocalDateTime.now();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(LocalDateTime ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void atualizarDados(UsuarioAtualizacaoDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.ultimaAlteracao = LocalDateTime.now();
    }
}

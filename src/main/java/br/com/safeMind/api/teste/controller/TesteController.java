package br.com.safeMind.api.teste.controller;

import br.com.safeMind.api.pergunta.model.Pergunta;
import br.com.safeMind.api.teste.dto.DadosCadastroTesteDTO;
import br.com.safeMind.api.teste.dto.TesteAtualizacaoDTO;
import br.com.safeMind.api.teste.model.Teste;
import br.com.safeMind.api.teste.service.TesteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/testes")
public class TesteController {

    @Autowired
    TesteService testeService;

    @PostMapping
    public ResponseEntity<Teste> cadastrar(@Valid @RequestBody DadosCadastroTesteDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(testeService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Teste>> listar(){
        return ResponseEntity.ok(testeService.listar());
    }

    @GetMapping("/{id}/perguntas")
    public ResponseEntity<List<Pergunta>> listarPerguntasTeste(@PathVariable UUID id){
        return ResponseEntity.ok(testeService.obterPerguntasTeste(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teste> pesquisarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(testeService.pesquisarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teste> atualizar(@PathVariable UUID id, @RequestBody TesteAtualizacaoDTO dto){
        return ResponseEntity.ok(testeService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id){
        testeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.safeMind.api.pergunta.controller;

import br.com.safeMind.api.pergunta.dto.DadosAtualizacaoPerguntaDTO;
import br.com.safeMind.api.pergunta.dto.DadosCadastroPergunta;
import br.com.safeMind.api.pergunta.model.Pergunta;
import br.com.safeMind.api.pergunta.service.PerguntaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    @Autowired
    PerguntaService perguntaService;


    @PostMapping
    public ResponseEntity<Pergunta> cadastrar(@RequestBody @Valid DadosCadastroPergunta dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(perguntaService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Pergunta>> listar(){
        return ResponseEntity.ok(perguntaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pergunta> buscarPorId(@PathVariable UUID id){
        return ResponseEntity.ok(perguntaService.pesquisarPorId(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pergunta> ataulizar(@PathVariable UUID id, @RequestBody @Valid DadosAtualizacaoPerguntaDTO dto){
        return ResponseEntity.ok(perguntaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id){
        perguntaService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}

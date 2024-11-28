package br.com.safeMind.api.resposta.controller;

import br.com.safeMind.api.resposta.dto.DadosCadastroRespostaDTO;
import br.com.safeMind.api.resposta.model.Resposta;
import br.com.safeMind.api.resposta.service.RespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    RespostaService respostaService;

    @PostMapping
    public ResponseEntity<Resposta>  cadastrar(@RequestBody @Valid DadosCadastroRespostaDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Resposta>> listar(){
        return ResponseEntity.ok(respostaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resposta> buscar(@PathVariable UUID id){
        return ResponseEntity.ok(respostaService.buscarPorId(id))
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resposta> atualizar(@PathVariable UUID id, @RequestBody @Valid DadosCadastroRespostaDTO dto){
        return ResponseEntity.ok(respostaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id){
        respostaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.safeMind.api.usuario.controller;

import br.com.safeMind.api.usuario.dto.UsuarioAtualizacaoDTO;
import br.com.safeMind.api.usuario.dto.UsuarioCadastroDTO;
import br.com.safeMind.api.usuario.model.Usuario;
import br.com.safeMind.api.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioCadastroDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> pesquisarPorId(@PathVariable("id") UUID id){
        return ResponseEntity.ok(usuarioService.pesquisarPorId(id));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> pesquisarPorCpf(@PathVariable("cpf") String cpf){
        return ResponseEntity.ok(usuarioService.pesquisarPorCpf(cpf));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> atualizarPorId(@PathVariable("id") UUID id, @RequestBody UsuarioAtualizacaoDTO dto){
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable("id") UUID id){
        usuarioService.deletarUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> excluirTodosUsuarios(){
        usuarioService.deletarTodosUsuarios();
        return ResponseEntity.noContent().build();
    }

}

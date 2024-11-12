package br.com.safeMind.api.usuario.service;

import br.com.safeMind.api.usuario.dto.UsuarioAtualizacaoDTO;
import br.com.safeMind.api.usuario.dto.UsuarioCadastroDTO;
import br.com.safeMind.api.usuario.model.Usuario;
import br.com.safeMind.api.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(UsuarioCadastroDTO dto){
        return usuarioRepository.save(new Usuario(dto));
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario pesquisarPorId(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Usuario atualizar(UUID id, UsuarioAtualizacaoDTO dto) {
        var usuario = pesquisarPorId(id);
        usuario.atualizarDados(dto);
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuarioPorId(UUID id) {
        var usuario = pesquisarPorId(id);
        usuarioRepository.delete(usuario);
    }

    public void deletarTodosUsuarios() {
        usuarioRepository.deleteAll();
    }
}

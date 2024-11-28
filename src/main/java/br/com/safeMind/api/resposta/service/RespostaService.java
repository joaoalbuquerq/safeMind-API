package br.com.safeMind.api.resposta.service;

import br.com.safeMind.api.comon.exception.RecursoNaoEncontradoException;
import br.com.safeMind.api.resposta.dto.DadosCadastroRespostaDTO;
import br.com.safeMind.api.resposta.model.Resposta;
import br.com.safeMind.api.resposta.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RespostaService {

    @Autowired
    RespostaRepository respostaRepository;

    public Resposta cadastrar(DadosCadastroRespostaDTO dto) {
        Resposta resposta = new Resposta(dto);
        return respostaRepository.save(resposta);
    }

    public List<Resposta> listar() {
        return respostaRepository.findAll();
    }

    public Resposta buscarPorId(UUID id) {
        return respostaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Resposta inexistente"));
    }

    public Resposta atualizar(UUID id, DadosCadastroRespostaDTO dto) {
        var resposta = buscarPorId(id);
        resposta.setDescricao(dto.descricao());
        return respostaRepository.save(resposta);
    }

    public void remover(UUID id) {
        var resposta = buscarPorId(id);
        respostaRepository.delete(resposta);
    }
}

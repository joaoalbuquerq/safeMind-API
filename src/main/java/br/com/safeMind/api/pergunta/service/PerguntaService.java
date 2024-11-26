package br.com.safeMind.api.pergunta.service;

import br.com.safeMind.api.comon.exception.RecursoNaoEncontradoException;
import br.com.safeMind.api.pergunta.dto.DadosAtualizacaoPerguntaDTO;
import br.com.safeMind.api.pergunta.dto.DadosCadastroPergunta;
import br.com.safeMind.api.pergunta.model.Pergunta;
import br.com.safeMind.api.pergunta.repository.PerguntaRepository;
import br.com.safeMind.api.teste.service.TesteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PerguntaService {

    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    TesteService testeService;

    public Pergunta cadastrar(DadosCadastroPergunta dto) {
        var teste = testeService.pesquisarPorId(dto.testeId());
        Pergunta pergunta = new Pergunta(dto, teste);
        return perguntaRepository.save(pergunta);
    }

    public List<Pergunta> listar() {
        return perguntaRepository.findAll();
    }

    public Pergunta pesquisarPorId(UUID id){
        return perguntaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pergunta não existe"));
    }

    public Pergunta atualizar(UUID id, DadosAtualizacaoPerguntaDTO dto) {
        var pergunta = pesquisarPorId(id);
        if(dto.descricao() != null && !dto.descricao().isBlank())
            pergunta.setDescricao(dto.descricao());

        return perguntaRepository.save(pergunta);
    }

    public void excluir(UUID id){
        var pergunta = pesquisarPorId(id);
        perguntaRepository.delete(pergunta);
    }
}

package br.com.safeMind.api.teste.service;

import br.com.safeMind.api.comon.exception.RecursoNaoEncontradoException;
import br.com.safeMind.api.pergunta.model.Pergunta;
import br.com.safeMind.api.pergunta.repository.PerguntaRepository;
import br.com.safeMind.api.pergunta.service.PerguntaService;
import br.com.safeMind.api.resposta.service.RespostaService;
import br.com.safeMind.api.respostaPerguntaTeste.dto.DadosCadastroRespostaTesteDTO;
import br.com.safeMind.api.respostaPerguntaTeste.model.RespostaPerguntaTeste;
import br.com.safeMind.api.respostaPerguntaTeste.repository.RespostaPerguntaTesteRepository;
import br.com.safeMind.api.teste.dto.DadosCadastroTesteDTO;
import br.com.safeMind.api.teste.dto.TesteAtualizacaoDTO;
import br.com.safeMind.api.teste.model.Teste;
import br.com.safeMind.api.teste.repository.TesteRepository;
import br.com.safeMind.api.usuario.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TesteService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RespostaService respostaService;

    @Autowired
    PerguntaService perguntaService;

    @Autowired
    TesteRepository testeRepository;

    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    RespostaPerguntaTesteRepository respostaTesteRepository;

    public Teste criar(DadosCadastroTesteDTO dto) {
        var teste = new Teste(dto);
        return testeRepository.save(teste);
    }

    public List<Teste> listar() {
        return testeRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Teste pesquisarPorId(UUID id) {
        return testeRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Teste inexistente"));
    }

    public Teste atualizar(UUID id, TesteAtualizacaoDTO dto) {
        var teste = pesquisarPorId(id);
        teste.setDescricao(dto.nome());
        teste.setDescricao(dto.descricao());
        teste.setUltimaAlteracao(LocalDateTime.now());

        return testeRepository.save(teste);
    }

    public void deletar(UUID id) {
        testeRepository.delete(pesquisarPorId(id));
    }

    public List<Pergunta> obterPerguntasTeste(UUID id) {
        return perguntaRepository.findByTesteId(id);
    }

    public void responderTeste(@Valid DadosCadastroRespostaTesteDTO dto) {

        var teste = pesquisarPorId(dto.testeId());
        var usuario =usuarioService.pesquisarPorId(dto.usuarioId());

        for (DadosCadastroRespostaTesteDTO.PerguntaRespostaDTO perguntaResposta : dto.perguntaResposta()) {
            var pergunta = perguntaService.pesquisarPorId(perguntaResposta.getPerguntaId());
            var resposta = respostaService.buscarPorId(perguntaResposta.getRespostaId());

            var respostaPergunta = new RespostaPerguntaTeste(teste,usuario,pergunta,resposta);
            respostaTesteRepository.save(respostaPergunta);
        }
    }

}

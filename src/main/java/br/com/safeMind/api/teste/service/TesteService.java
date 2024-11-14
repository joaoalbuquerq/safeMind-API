package br.com.safeMind.api.teste.service;

import br.com.safeMind.api.teste.dto.DadosCadastroTesteDTO;
import br.com.safeMind.api.teste.model.Teste;
import br.com.safeMind.api.teste.repository.TesteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TesteService {

    @Autowired
    TesteRepository testeRepository;

    public Teste criar(DadosCadastroTesteDTO dto) {
        var teste = new Teste(dto);
        return testeRepository.save(teste);
    }

    public List<Teste> listar() {
        return testeRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Teste pesquisarPorId(UUID id) {
        return testeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
}

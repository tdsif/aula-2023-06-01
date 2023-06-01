package br.edu.ifrs.riogrande.tads.cobaia.domain.aluno;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlunoService {

  private final AlunoRepository alunoRepository;
  private final ObjectMapper objectMapper;


  public List<AlunoDTO> getAlunos() {
    final boolean isParallel = false;
    return StreamSupport
      .stream(alunoRepository.findAll().spliterator(), isParallel)
        .map(a -> AlunoDTO.builder()
          .id(a.getId())
          .nome(a.getNome())
          .email(a.getEmail())
          .build())
      .toList();
  }

  public Optional<Aluno> findById(@NonNull Long id) {
    return alunoRepository.findById(id);
  }

  // garantia de não-nulidade
  public void salvar(@NonNull AlunoDTO alunoDTO) {
    
    // Aluno a = objectMapper.readValue(objectMapper.writeValueAsString(alunoDTO), Aluno.class);
    // conversão?
    Aluno aluno = Aluno.builder()
      .nome(alunoDTO.getNome())
      .email(alunoDTO.getEmail())
      .build();

    alunoRepository.save(aluno);
  }
  

  public void excluir(Long id) {

    Aluno aluno = alunoRepository.findById(id)
      .orElseThrow(() -> new IllegalStateException("Aluno não existe"));

    alunoRepository.delete(aluno);
  }

  @PostConstruct
  void popularBanco() {
    System.out.println("Populando o mochinho ...");

    Aluno a = new Aluno();
    a.setNome("Marcio");
    a.setEmail("marcio.torres@riogrande.ifrs.edu.br");

    a = alunoRepository.save(a);

    System.out.println(alunoRepository.getClass());

    System.out.println("Aluno salvo " + a.getId());

    System.out.println(alunoRepository.findAll());
  }
}

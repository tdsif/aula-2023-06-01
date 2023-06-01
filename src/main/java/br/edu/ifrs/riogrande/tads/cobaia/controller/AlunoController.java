package br.edu.ifrs.riogrande.tads.cobaia.controller;

import static java.util.Objects.requireNonNull;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import br.edu.ifrs.riogrande.tads.cobaia.domain.aluno.AlunoDTO;
import br.edu.ifrs.riogrande.tads.cobaia.domain.aluno.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/alunos") // resource (recurso) => plural
public class AlunoController {
  
  private final AlunoService alunoService;
  
  @DeleteMapping("/{id}")
  public ResponseEntity<?> excluir(@PathVariable Long id) {
    alunoService.excluir(id);
    return ResponseEntity.ok().build(); // 200
  }

  @PostMapping
  public ResponseEntity<?> novoAluno(@Valid @RequestBody AlunoDTO aluno) {

    alunoService.salvar(Objects.requireNonNull(aluno, "Aluno é null"));

    // http://localhost:9090/api/v1/alunos/{id}
    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(aluno.getId())
      .toUri();

    return ResponseEntity.created(location).build(); // 201 CREATED Location: URL do recurso criado
  }


  @GetMapping
  public List<AlunoDTO> getAlunos() {
    return alunoService.getAlunosOrderByNome();
  }

  @PutMapping //api/v1/alunos
  public ResponseEntity<List<Long>> atualizar(@RequestBody List<AlunoDTO> dtos) {

    List<Long> idsFalharam = alunoService.atualizarEmLote(dtos);

    return ResponseEntity.ok().body(idsFalharam);
  }

  @PutMapping("/{id}") //alunos/1
  public ResponseEntity<?> atualizar(
    @PathVariable("id") Long id,
    @RequestBody AlunoDTO dto) {

    alunoService.atualizar(id, dto);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}") // ex.: /alunos?id=1 (? query param) ou /alunos/1 (recurso com uma identidade)
  public ResponseEntity<AlunoDTO> getAluno(@PathVariable("id") Long id) {

    Optional<AlunoDTO> aluno = alunoService.findById(
      requireNonNull(id, "O id é obrigatório"));

    if (aluno.isPresent()) {
      return ResponseEntity.ok(aluno.get()); // 200 {"nome": "Marcio"}
      // return ResponseEntity.ok().body(aluno.get());
    } else {
      // return ResponseEntity.noContent().build(); // 204
      // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      // return ResponseEntity.status(404).build();
      return ResponseEntity.notFound().build();
    }
  }
}

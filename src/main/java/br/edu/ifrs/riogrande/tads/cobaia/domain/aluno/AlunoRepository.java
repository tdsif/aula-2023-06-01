package br.edu.ifrs.riogrande.tads.cobaia.domain.aluno;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface AlunoRepository // <TipoEntidade, TipoId>
                extends Repository<Aluno, Long> {

  List<Aluno> findAll();

  Optional<Aluno> findById(Long id);

  void delete(Aluno aluno);

  Aluno save(Aluno a);
  
}


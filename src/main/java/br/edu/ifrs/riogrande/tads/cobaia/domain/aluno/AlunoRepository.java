package br.edu.ifrs.riogrande.tads.cobaia.domain.aluno;

import org.springframework.data.repository.CrudRepository;

public interface AlunoRepository // <TipoEntidade, TipoId>
                extends CrudRepository<Aluno, Long> {
  
}


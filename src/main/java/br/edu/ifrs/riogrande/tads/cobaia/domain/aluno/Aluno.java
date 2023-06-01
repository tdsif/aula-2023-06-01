package br.edu.ifrs.riogrande.tads.cobaia.domain.aluno;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alunos")
// sem visibilidade: privado do pacote
public class Aluno { // Java Bean
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // sequencial
  private Long id;

  @Column(length = 50, nullable = false, name = "db_rs5_nome")
  @JsonProperty("nomeCompleto") // body/endpoint
  // @jakarta.validation.constraints.*
  @NotNull(message = "O nome é obrigatório")
  @NotBlank(message = "O nome não pode estar em branco")
  private String nome;

  // @Min(value = 10, message = "O aluno deve ter a partir de 10 anos")
  // private Integer idade;

  // // 11230171
  // @Pattern(regexp = "^\\d{8}$", message = "A matrícula deve ter 8 dígitos")
  // private String matricula;

  // Sem length considera 255
  @Column(length = 100, nullable = false, unique = true)
  private String email;

}

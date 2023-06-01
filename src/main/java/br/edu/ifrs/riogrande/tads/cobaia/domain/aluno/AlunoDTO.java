package br.edu.ifrs.riogrande.tads.cobaia.domain.aluno;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

// Data Transfer Object (intercâmbio), ViewModel
// Modelo tanto para o tráfego, quanto validação
@Data
@Builder
public class AlunoDTO {

  private Long id;

  @JsonProperty("nomeCompleto") // body/endpoint
  // @jakarta.validation.constraints.*
  @NotNull(message = "O nome é obrigatório")
  @NotBlank(message = "O nome não pode estar em branco")
  private String nome;

  private String email;

  // @Min(value = 10, message = "O aluno deve ter a partir de 10 anos")
  // private Integer idade;

  // // 11230171
  // @Pattern(regexp = "^\\d{8}$", message = "A matrícula deve ter 8 dígitos")
  // private String matricula;

  // Sem length considera 255
}

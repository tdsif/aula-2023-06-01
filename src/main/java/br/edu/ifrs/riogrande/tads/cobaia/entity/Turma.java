package br.edu.ifrs.riogrande.tads.cobaia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Turma {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long codigo;
  
  @Column
  String descricao;
  
  @Column
  private Integer ano;

}

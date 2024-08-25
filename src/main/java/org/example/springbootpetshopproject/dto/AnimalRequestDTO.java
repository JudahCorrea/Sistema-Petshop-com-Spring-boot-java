package org.example.springbootpetshopproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRequestDTO {
    String nome;
    String especie;
    String raca;
    int idade;
    UsuarioRequestDTO tutor;

}

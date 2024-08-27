package org.example.springbootpetshopproject.dto;

import org.example.springbootpetshopproject.domain.Animal;
import org.example.springbootpetshopproject.domain.Usuario;

public record AnimalAtualizarDTO(
        String nome,
        String raca,
        String especie,
        Integer idade

) {
    public AnimalAtualizarDTO(Animal animal) {
        this(animal.getNome(), animal.getRaca(), animal.getEspecie(), animal.getIdade());
    }
}

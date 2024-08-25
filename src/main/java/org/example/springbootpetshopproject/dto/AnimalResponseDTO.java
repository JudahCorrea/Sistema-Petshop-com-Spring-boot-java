package org.example.springbootpetshopproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootpetshopproject.controller.AnimalController;
import org.example.springbootpetshopproject.controller.UsuarioController;
import org.example.springbootpetshopproject.domain.Animal;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalResponseDTO extends RepresentationModel<AnimalResponseDTO> {
    String nome;
    String especie;
    String raca;
    int idade;
    UsuarioResponseDTO tutor;

    public void adicionarLinks(Animal animal) {
        this.add(linkTo(AnimalController.class).slash(animal.getId()).withSelfRel());
        this.add(linkTo(UsuarioController.class).slash(animal.getUsuario().getId()).withRel("usuario"));
    }
}

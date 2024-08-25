package org.example.springbootpetshopproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootpetshopproject.controller.EnderecoController;
import org.example.springbootpetshopproject.controller.UsuarioController;
import org.example.springbootpetshopproject.domain.Usuario;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO extends RepresentationModel<UsuarioResponseDTO> {
    String nome;
    String email;
    String contato;
    EnderecoRequestDTO endereco;

    public void adicionarLinks(Usuario usuario) {
        this.add(linkTo(UsuarioController.class).slash(usuario.getId()).withSelfRel());
        this.add(linkTo(EnderecoController.class).slash(usuario.getEndereco().getId()).withRel("endereco"));
    }
}

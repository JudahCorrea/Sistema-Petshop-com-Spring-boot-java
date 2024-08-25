package org.example.springbootpetshopproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
    String nome;
    String email;
    String senha;
    String contato;
    EnderecoRequestDTO endereco;
    RoleDTO role;

    public UsuarioRequestDTO(UsuarioCadastroDTO usuario) {
        this.nome = usuario.nome();
        this.email = usuario.email();
        this.senha = usuario.senha();
        this.contato = usuario.contato();
        this.endereco = new EnderecoRequestDTO(usuario.endereco());

    }
}

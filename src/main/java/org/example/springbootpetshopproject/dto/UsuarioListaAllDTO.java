package org.example.springbootpetshopproject.dto;

import org.example.springbootpetshopproject.domain.Endereco;
import org.example.springbootpetshopproject.domain.Usuario;

public record UsuarioListaAllDTO(
        Long id,
        String nome,
        String email,
        String contato,
        Endereco endereco
) {
    public UsuarioListaAllDTO(Usuario usuario) {
        this(usuario.getId(),usuario.getNome(),usuario.getEmail(),usuario.getContato(),usuario.getEndereco());
    }
}

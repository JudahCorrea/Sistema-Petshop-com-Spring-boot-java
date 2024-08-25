package org.example.springbootpetshopproject.dto;

import org.example.springbootpetshopproject.domain.Endereco;
import org.example.springbootpetshopproject.domain.UserRole;

public record UsuarioCadastroDTO(
        String nome,
        String email,
        String senha,
        String contato,
        Endereco endereco
) {
}

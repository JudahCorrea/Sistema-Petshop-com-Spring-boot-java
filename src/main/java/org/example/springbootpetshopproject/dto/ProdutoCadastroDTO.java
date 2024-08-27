package org.example.springbootpetshopproject.dto;

public record ProdutoCadastroDTO (
        String nome,
        int quantidade,
        float preco,
        String descricao
){
}

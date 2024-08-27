package org.example.springbootpetshopproject.dto;

import org.example.springbootpetshopproject.domain.Produto;

public record ProdutoListar(
        Long id,
        String nome,
        int quantidade,
        float preco,
        String descricao
) {



}

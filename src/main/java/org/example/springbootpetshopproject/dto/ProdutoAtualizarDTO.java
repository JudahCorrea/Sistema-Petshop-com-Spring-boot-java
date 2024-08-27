package org.example.springbootpetshopproject.dto;

public record ProdutoAtualizarDTO(
        String nome,
        int quantidade,
        float preco,
        String descricao,
        boolean estoque
) {

    public ProdutoAtualizarDTO(ProdutoAtualizarDTO produto) {
        this(produto.nome, produto.quantidade, produto.preco, produto.descricao, produto.estoque);
    }
}

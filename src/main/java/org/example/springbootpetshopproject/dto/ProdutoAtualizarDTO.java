package org.example.springbootpetshopproject.dto;

public record ProdutoAtualizarDTO(
        String nome,
        int quantidade,
        float preco,
        String descricao,
        boolean estoque
) {
    public ProdutoAtualizarDTO(ProdutoAtualizarDTO produto) {
        this.nome = produto.nome;
        this.quantidade = produto.quantidade;
        this.preco = produto.preco;
        this.descricao = produto.descricao;
        this.estoque = produto.estoque;
    }
}

package org.example.springbootpetshopproject.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.springbootpetshopproject.domain.Produto;
import org.example.springbootpetshopproject.dto.ProdutoRequestDTO;
import org.example.springbootpetshopproject.dto.ProdutoResponseDTO;
import org.example.springbootpetshopproject.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos/")
public class ProdutoController {
    private final ProdutoService service;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody @Valid ProdutoRequestDTO produto) {
        Produto produtoEntidade = service.criar(converterParaEntidade(produto));
        return ResponseEntity.ok(converterParaDTO(produtoEntidade));
    }


    private ProdutoResponseDTO converterParaDTO(Produto produtoEntidade) {
        ProdutoResponseDTO produtoDTO = mapper.map(produtoEntidade, ProdutoResponseDTO.class);
        return produtoDTO;
    }

    private Produto converterParaEntidade(ProdutoRequestDTO produtoDTO) {
        Produto produtoEntidade = mapper.map(produtoDTO, Produto.class);
        return produtoEntidade;
    }
}

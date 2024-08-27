package org.example.springbootpetshopproject.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.springbootpetshopproject.domain.Produto;
import org.example.springbootpetshopproject.dto.ProdutoAtualizarDTO;
import org.example.springbootpetshopproject.dto.ProdutoCadastroDTO;
import org.example.springbootpetshopproject.dto.ProdutoRequestDTO;
import org.example.springbootpetshopproject.dto.ProdutoResponseDTO;
import org.example.springbootpetshopproject.repository.IProdutoRepository;
import org.example.springbootpetshopproject.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos/")
public class ProdutoController {
    private final ProdutoService service;
    private final ModelMapper mapper;

    @Autowired
    IProdutoRepository repository;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody ProdutoCadastroDTO produto) {
       var produtoEntidade = new Produto(produto);
       produtoEntidade.setEstoque(true);
       repository.save(produtoEntidade);
       return ResponseEntity.ok(converterParaDTO(produtoEntidade));

    }

  /*@DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        var produtoEntidade = repository.findById(id);

    }"*/
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity atualizar (@RequestBody ProdutoAtualizarDTO produto, Long id) {
        var produtoAtualizar = repository.getReferenceById(id);
        produtoAtualizar.atualizar(produto);
        return ResponseEntity.ok(new ProdutoAtualizarDTO(produto));
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

package org.example.springbootpetshopproject.service;

import org.example.springbootpetshopproject.domain.Produto;
import org.example.springbootpetshopproject.repository.IProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends GenericoService<Produto, IProdutoRepository>{

    public ProdutoService(IProdutoRepository repository) {
        super(repository);
    }
}

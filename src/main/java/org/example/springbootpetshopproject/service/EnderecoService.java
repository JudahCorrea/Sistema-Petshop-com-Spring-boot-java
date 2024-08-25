package org.example.springbootpetshopproject.service;

import org.example.springbootpetshopproject.domain.Endereco;
import org.example.springbootpetshopproject.repository.IEnderecoRepository;
import org.springframework.stereotype.Service;



@Service
public class EnderecoService extends GenericoService<Endereco, IEnderecoRepository> {

    public EnderecoService(IEnderecoRepository repository) {
        super(repository);
    }

    /*@Override
    public Endereco comparar(Endereco endereco) {
        return endereco = repository.findByRuaNumero(endereco.getRua(), endereco.getNumero());
    }*/
}

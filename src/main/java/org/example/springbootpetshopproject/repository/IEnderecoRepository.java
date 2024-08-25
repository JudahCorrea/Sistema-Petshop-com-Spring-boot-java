package org.example.springbootpetshopproject.repository;


import org.example.springbootpetshopproject.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
   // public Endereco findByRuaNumero(String rua, Integer numero);

}

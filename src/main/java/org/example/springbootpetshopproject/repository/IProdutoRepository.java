package org.example.springbootpetshopproject.repository;

import org.example.springbootpetshopproject.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {
    Page findAllByDeletadoEmNull(Pageable paginacao);
}

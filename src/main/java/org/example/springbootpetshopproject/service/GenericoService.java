package org.example.springbootpetshopproject.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class GenericoService<T, REPO extends JpaRepository<T, Long>> implements IService<T>{
    protected final REPO repository;

    public GenericoService(REPO repository) {
        this.repository = repository;
    }

    @Override
    public T criar(T entity){
        return repository.save(entity);
    }

    @Override
    public T alterar(T entity, Long id) {

        T entidadeExiste;
        try {
            entidadeExiste = this.listarPorId(id);
            BeanUtils.copyProperties(entity, entidadeExiste, "id");
            return this.repository.saveAndFlush(entidadeExiste);
        } catch (Exception e) {
            return this.criar(entity);
        }



    }


    @Override
    public Page<T> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T listarPorId(Long id) {
        Optional<T> op = repository.findById(id);

        if(op.isPresent())
            return op.get();

        throw new EntityNotFoundException("Objeto do id: " + id + " não foi encontrado");
    }

    @Override
    public void deletarPorId(Long id) {
        T entity = this.listarPorId(id); // verificar caso nao exista a entidade com o id
        this.repository.delete(entity);
    }


    @Override
    public T comparar(T entity) {
        return entity;
    }
}

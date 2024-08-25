package org.example.springbootpetshopproject.service;
import org.example.springbootpetshopproject.domain.Animal;

import org.example.springbootpetshopproject.repository.IAnimalRepository;
import org.springframework.stereotype.Service;

@Service
public class AnimalService extends GenericoService<Animal, IAnimalRepository>{

    public AnimalService(IAnimalRepository repository) {
        super(repository);
    }


}

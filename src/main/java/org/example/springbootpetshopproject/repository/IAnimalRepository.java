package org.example.springbootpetshopproject.repository;

import org.example.springbootpetshopproject.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnimalRepository extends JpaRepository<Animal, Long> {

}

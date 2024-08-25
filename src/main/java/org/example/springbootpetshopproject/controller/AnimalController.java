package org.example.springbootpetshopproject.controller;


import lombok.AllArgsConstructor;
import org.example.springbootpetshopproject.domain.Animal;
import org.example.springbootpetshopproject.domain.Usuario;
import org.example.springbootpetshopproject.dto.AnimalRequestDTO;
import org.example.springbootpetshopproject.dto.AnimalResponseDTO;
import org.example.springbootpetshopproject.service.AnimalService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/animais/")
public class AnimalController {
    private final AnimalService service;
    private final ModelMapper mapper;


    @PostMapping
    public ResponseEntity<AnimalResponseDTO> criar(@RequestBody AnimalRequestDTO animal) {
        Animal animalEntidade = service.criar(converterParaEntidade(animal));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(animalEntidade.getId())
                .toUri();

        return ResponseEntity.created(uri).body(converterParaDTO(animalEntidade));
    }

    @GetMapping
    public Page<AnimalResponseDTO> listarTodos(Pageable pageable) {
        Page<Animal> animaisPage = service.listarTodos(pageable);

        return animaisPage.map(this::converterParaDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimalResponseDTO> listarPorId(@PathVariable("id") Long id) {
        Animal animalEntidade = service.listarPorId(id);
        AnimalResponseDTO animalDTO = mapper.map(animalEntidade, AnimalResponseDTO.class);

        return ResponseEntity.ok(animalDTO);
    }


    // fazer logica no generico
    @PutMapping("{id}")
    public ResponseEntity<AnimalResponseDTO> alterar(@RequestBody AnimalRequestDTO animalDTO, @PathVariable("id") Long id) {

        try {
            Animal animalEntidade = service.listarPorId(id);
        } catch(Exception e) {
            return this.criar(animalDTO);
        }

        Animal animalAtualizado = service.alterar(mapper.map(animalDTO, Animal.class), id);
        return ResponseEntity.ok(converterParaDTO(animalAtualizado));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") Long id) {
        service.deletarPorId(id);
    }



    // METODOS CONVERSORES DE ENTIDADE E DTO
    private AnimalResponseDTO converterParaDTO(Animal animalEntidade) {
        AnimalResponseDTO animalDTO = mapper.map(animalEntidade, AnimalResponseDTO.class);
        animalDTO.adicionarLinks(animalEntidade);
        return animalDTO;
    }

    private Animal converterParaEntidade(AnimalRequestDTO animalDTO) {
        Animal animalEntidade = mapper.map(animalDTO, Animal.class);
        Usuario usuarioEntidade = mapper.map(animalDTO.getTutor(), Usuario.class);
        animalEntidade.setUsuario(usuarioEntidade);
        return animalEntidade;
    }
}

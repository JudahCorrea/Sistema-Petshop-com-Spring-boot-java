package org.example.springbootpetshopproject.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@RequiredArgsConstructor
public abstract class ModelGenerico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDateTime deletadoEm;
}

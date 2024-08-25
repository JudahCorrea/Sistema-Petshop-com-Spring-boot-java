package org.example.springbootpetshopproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Produto extends ModelGenerico {

    @NotEmpty(message = "O nome do produto precisa ser preenchido.")
    String nome;

    @DecimalMin(value = "0", message = "A quantidade do produto precisa ser maior que 0")
    int quantidade;

    @DecimalMin(value = "0", message = "O valor do produto ser maior que 0.")
    Float preco;

    Boolean estoque = false;

    @Length(min = 2, max = 100, message = "A descrição do produto precisa conter mais do que {min} caracteres e menos do que {max} caracteres.")
    String descricao ;
}
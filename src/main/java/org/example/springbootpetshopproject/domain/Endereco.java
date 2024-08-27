package org.example.springbootpetshopproject.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.springbootpetshopproject.dto.EnderecoRequestDTO;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE Endereco SET deletado_Em = CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deletado_Em is null")
public class Endereco extends ModelGenerico {

    @NotNull(message = "A rua não pode ser nula.")
    String rua;

    @NotNull(message = "O número não pode ser nulo.")
    Integer numero;

    public Endereco (EnderecoRequestDTO dados){
        this.rua = dados.getRua();
        this.numero = dados.getNumero();
    }
}

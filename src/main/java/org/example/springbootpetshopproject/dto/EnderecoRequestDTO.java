package org.example.springbootpetshopproject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootpetshopproject.domain.Endereco;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {
    String rua;
    int numero;

    public EnderecoRequestDTO(Endereco endereco) {
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
    }
}

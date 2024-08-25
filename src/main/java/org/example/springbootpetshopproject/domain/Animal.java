package org.example.springbootpetshopproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SQLDelete(sql = "UPDATE Animal SET deletado_em = CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deletado_em is null")
public class Animal extends ModelGenerico {

    @NotNull(message = "O nome não pode ser nulo.")
    @NotEmpty(message = "O nome precisa ser preenchido.")
    @Length(min = 2, max = 60, message = "O nome precisa conter mais do que {min} caracteres e menos do que {max} caracteres.")
    String nome;

    @NotNull(message = "A espécie não pode ser nula.")
    @NotEmpty(message = "A espécie precisa ser preenchida.")
    @Length(min = 2, max = 25, message = "A espécie precisa conter mais do que {min} caracteres e menos do que {max} caracteres.")
    String especie;

    @NotNull(message = "A raça não pode ser nula.")
    @NotEmpty(message = "A raça precisa ser preenchida.")
    @Size(min = 2, max = 25, message = "A raça precisa conter mais do que {min} caracteres e menos do que {max} caracteres.")
    String raca;

    @DecimalMin(value = "0", message = "A idade precisa ser maior que 0")
    Integer idade;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario")
    Usuario tutor;


    // metodos auxiliares para manter o mapeamento entre Usuario e Animal
    public void setUsuario(Usuario usuario) {
        this.tutor = usuario;
    }

    public Usuario getUsuario() {
        return tutor;
    }
}

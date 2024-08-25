package org.example.springbootpetshopproject.domain;

import jakarta.persistence.*;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carrinho extends ModelGenerico {

   /*
   @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "carrinho_produto",
            joinColumns = {
                    @JoinColumn(name = "id_carrinho", referencedColumnName = "id_carrinho")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "id_produto")
            }
    )
    List<Produto> produtos = new ArrayList<Produto>();
    */
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // metodos auxiliares para manter o mapeamento entre Usuario e Carrinho
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }
}
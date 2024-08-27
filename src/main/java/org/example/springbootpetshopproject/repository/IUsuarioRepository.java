package org.example.springbootpetshopproject.repository;

import aj.org.objectweb.asm.commons.Remapper;
import org.example.springbootpetshopproject.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Page<Usuario> findAllByDeletadoEmNull(Pageable paginacao);
}

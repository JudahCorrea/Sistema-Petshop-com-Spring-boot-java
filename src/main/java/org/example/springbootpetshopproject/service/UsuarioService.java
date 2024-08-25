package org.example.springbootpetshopproject.service;



import org.example.springbootpetshopproject.domain.Usuario;
import org.example.springbootpetshopproject.repository.IUsuarioRepository;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService extends GenericoService<Usuario, IUsuarioRepository> {


    public UsuarioService(IUsuarioRepository repository) {
        super(repository);
    }


    @Override
    public Usuario comparar(Usuario usuario) {
         return usuario = repository.findByEmail(usuario.getEmail());
    }

}

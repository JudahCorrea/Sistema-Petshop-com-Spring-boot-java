package org.example.springbootpetshopproject.controller;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.springbootpetshopproject.domain.Endereco;
import org.example.springbootpetshopproject.domain.UserRole;
import org.example.springbootpetshopproject.domain.Usuario;
import org.example.springbootpetshopproject.dto.*;
import org.example.springbootpetshopproject.repository.IUsuarioRepository;
import org.example.springbootpetshopproject.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios/")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    private final UsuarioService service;
    //private final EnderecoService serviceEndereco;
    private final ModelMapper mapper;

    private IUsuarioRepository repository;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioCadastroDTO usuario) {
        if(this.repository.findByEmail(usuario.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.senha());
        Usuario usuarioCadastro = new Usuario(usuario.nome(), usuario.email(), encryptedPassword, usuario.contato(), usuario.endereco());
        usuarioCadastro.setRole(UserRole.USER);

        this.repository.save(usuarioCadastro);
        Usuario usuarioEntidade;

       var usuarioRetorno = new UsuarioRequestDTO(usuario);

        // Endereco consultaEndereco;
            usuarioEntidade = service.comparar(converterParaEntidade(usuarioRetorno));
            //consultaEndereco = serviceEndereco.comparar(converterEnderecoEntidade(usuario.getEndereco()));

            //if(consultaEndereco != null)
            //    usuario.setEndereco(converterEnderecoDTO(consultaEndereco));


        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(usuarioEntidade.getId())
                .toUri();

        return ResponseEntity.created(uri).body(converterParaDTO(usuarioEntidade));
    }


    @PostMapping("/admin")
    public ResponseEntity<UsuarioResponseDTO> criarAdmin(@RequestBody UsuarioCadastroDTO usuario) {
        if(this.repository.findByEmail(usuario.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.senha());
        Usuario usuarioCadastro = new Usuario(usuario.nome(), usuario.email(), encryptedPassword, usuario.contato(), usuario.endereco());
        usuarioCadastro.setRole(UserRole.ADMIN);

        this.repository.save(usuarioCadastro);
        Usuario usuarioEntidade;

        var usuarioRetorno = new UsuarioRequestDTO(usuario);

        // Endereco consultaEndereco;
        usuarioEntidade = service.comparar(converterParaEntidade(usuarioRetorno));
        //consultaEndereco = serviceEndereco.comparar(converterEnderecoEntidade(usuario.getEndereco()));

        //if(consultaEndereco != null)
        //    usuario.setEndereco(converterEnderecoDTO(consultaEndereco));


        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(usuarioEntidade.getId())
                .toUri();

        return ResponseEntity.created(uri).body(converterParaDTO(usuarioEntidade));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioListaAllDTO>> listar (Pageable paginacao){
        var page =  repository.findAllByDeletadoEmNull(paginacao).map(UsuarioListaAllDTO::new);
        return ResponseEntity.ok((Page<UsuarioListaAllDTO>) page);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDTO> listarPorId(@PathVariable("id") Long id) {
        Usuario usuarioEntidade = service.listarPorId(id);
        UsuarioResponseDTO usuarioDTO = mapper.map(usuarioEntidade, UsuarioResponseDTO.class);

        return ResponseEntity.ok(usuarioDTO);
    }


    // fazer logica no generico
    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<UsuarioResponseDTO> alterar(@RequestBody UsuarioRequestDTO usuarioDTO, @PathVariable("id") Long id) {
        Usuario usuarioAtualizado = repository.getReferenceById(id);
        usuarioAtualizado.atualizarDados(usuarioDTO);
        return ResponseEntity.ok(converterParaDTO(usuarioAtualizado));
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }


    // METODOS CONVERSORES DE ENTIDADE E DTO
    private UsuarioResponseDTO converterParaDTO(Usuario usuarioEntidade) {
        UsuarioResponseDTO usuarioDTO = mapper.map(usuarioEntidade, UsuarioResponseDTO.class);
        usuarioDTO.adicionarLinks(usuarioEntidade);
        return usuarioDTO;
    }

    private Usuario converterParaEntidade(UsuarioRequestDTO usuarioDTO) {
        Usuario usuarioEntidade = mapper.map(usuarioDTO, Usuario.class);
        Endereco enderecoEntidade = mapper.map(usuarioDTO.getEndereco(), Endereco.class);
        usuarioEntidade.setEndereco(enderecoEntidade);
        return usuarioEntidade;
    }



    // talvez mude
    private EnderecoRequestDTO converterEnderecoDTO(Endereco enderecoEntidade) {
        EnderecoRequestDTO enderecoDTO = mapper.map(enderecoEntidade, EnderecoRequestDTO.class);
        return enderecoDTO;
    }


    private Endereco converterEnderecoEntidade(EnderecoRequestDTO enderecoDTO) {
        Endereco enderecoEntidade = mapper.map(enderecoDTO, Endereco.class);
        return enderecoEntidade;
    }


}

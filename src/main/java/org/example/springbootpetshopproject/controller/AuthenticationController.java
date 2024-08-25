package org.example.springbootpetshopproject.controller;

import jakarta.validation.Valid;
import org.example.springbootpetshopproject.domain.Usuario;
import org.example.springbootpetshopproject.dto.AuthenticationDTO;
import org.example.springbootpetshopproject.dto.LoginRespondeDTO;
import org.example.springbootpetshopproject.infra.security.TokenService;
import org.example.springbootpetshopproject.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUsuarioRepository repository;

    @Autowired
    private TokenService tokenService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginRespondeDTO(token));
    }

}

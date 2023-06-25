package br.com.agenda.controller;

import br.com.agenda.dto.UsuarioRequest;
import br.com.agenda.dto.UsuarioResponse;
import br.com.agenda.mapper.UsuarioMapper;
import br.com.agenda.model.Usuario;
import br.com.agenda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControllerPost{
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioControllerPost(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody @Validated UsuarioRequest usuarioRequest) {
        Usuario novoUsuario = new Usuario(usuarioRequest.getNome(), usuarioRequest.getAniversario(),
                usuarioRequest.getCpf(), usuarioRequest.getEmail(), usuarioRequest.getTelefone());

        Usuario usuarioCriado = usuarioService.createUsuario(novoUsuario);

        UsuarioResponse usuarioResponse = new UsuarioResponse(usuarioCriado.getId(), usuarioCriado.getNome(),
                usuarioCriado.getAniversario(), usuarioCriado.getCpf(), usuarioCriado.getEmail(),
                usuarioCriado.getTelefone());

        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }
}

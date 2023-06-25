package br.com.agenda.controller;

import br.com.agenda.dto.UsuarioResponse;
import br.com.agenda.mapper.UsuarioMapper;
import br.com.agenda.model.Usuario;
import br.com.agenda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControllerGet {
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioControllerGet(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping("{/id}")
    public ResponseEntity<UsuarioResponse> pegarUmUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.getOneUser(id);

        if (usuario != null){
            UsuarioResponse usuarioResponse = new UsuarioResponse(usuario.getId(), usuario.getNome(),
                    usuario.getAniversario(), usuario.getCpf(), usuario.getEmail(), usuario.getTelefone());

            return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        List<UsuarioResponse> usuarioResponses = usuarioMapper.mapToUsuarioResponseList(usuarios);
        return ResponseEntity.ok(usuarioResponses);
    }
}

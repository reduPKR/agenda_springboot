package br.com.agenda.controller;

import br.com.agenda.dto.UsuarioRequest;
import br.com.agenda.dto.UsuarioResponse;
import br.com.agenda.mapper.UsuarioMapper;
import br.com.agenda.model.Usuario;
import br.com.agenda.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerPostTest {
    @InjectMocks
    private UsuarioControllerPost usuarioControllerPost;
    @Mock
    private UsuarioService usuarioService;
    @Mock
    private UsuarioMapper usuarioMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioControllerPost = new UsuarioControllerPost(usuarioService, usuarioMapper);
    }

    @Test
    void criarUsuario() {
        LocalDate data = LocalDate.of(1994, 2, 28);
        UsuarioRequest usuarioRequest = new UsuarioRequest("Rafael", data, "123.454.789-10", "rafa@teste.com", "123456789");

        Usuario novoUsuario = new Usuario(usuarioRequest.getNome(), usuarioRequest.getAniversario(),
                usuarioRequest.getCpf(), usuarioRequest.getEmail(), usuarioRequest.getTelefone());
        Usuario usuarioCriado = new Usuario("Rafael", data, "123.454.789-10", "rafa@teste.com", "123456789");
        usuarioCriado.setId(1L);

        UsuarioResponse usuarioResponse = new UsuarioResponse(usuarioCriado.getId(), usuarioCriado.getNome(),
                usuarioCriado.getAniversario(), usuarioCriado.getCpf(), usuarioCriado.getEmail(),
                usuarioCriado.getTelefone());

        when(usuarioService.createUsuario(ArgumentMatchers.any(Usuario.class))).thenReturn(usuarioCriado);

        ResponseEntity<UsuarioResponse> response = usuarioControllerPost.criarUsuario(usuarioRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals(usuarioResponse.getId(), response.getBody().getId());
        assertEquals(usuarioResponse.getNome(), response.getBody().getNome());
        assertEquals(usuarioResponse.getAniversario(), response.getBody().getAniversario());
        assertEquals(usuarioResponse.getCpf(), response.getBody().getCpf());
        assertEquals(usuarioResponse.getEmail(), response.getBody().getEmail());
        assertEquals(usuarioResponse.getTelefone(), response.getBody().getTelefone());
    }
}
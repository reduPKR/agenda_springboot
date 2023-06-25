package br.com.agenda.controller;

import br.com.agenda.dto.UsuarioResponse;
import br.com.agenda.mapper.UsuarioMapper;
import br.com.agenda.model.Usuario;
import br.com.agenda.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerGetTest {

    @InjectMocks
    private UsuarioControllerGet usuarioControllerGet;
    @Mock
    private UsuarioService usuarioService;
    @Mock
    private UsuarioMapper usuarioMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioControllerGet = new UsuarioControllerGet(usuarioService, usuarioMapper);
    }

    @Test
    void getUsuarios() {
        LocalDate data = LocalDate.of(1994, 2, 28);
        List<Usuario> usuarios = Arrays.asList(
                new Usuario("Rafael", data, "123.454.789-10", "rafa@teste.com", "123456789"),
                new Usuario("João", data, "987.654.321-00", "joao@teste.com", "987654321")
        );
        List<UsuarioResponse> usuariosResponse = Arrays.asList(
                new UsuarioResponse(1L, "Rafael", data, "123.454.789-10", "rafa@teste.com", "123456789"),
                new UsuarioResponse(2L, "João", data, "987.654.321-00", "joao@teste.com", "987654321")
        );

        when(usuarioService.getUsuarios()).thenReturn(usuarios);
        when(usuarioMapper.mapToUsuarioResponseList(usuarios)).thenReturn(usuariosResponse);

        ResponseEntity<List<UsuarioResponse>> response = usuarioControllerGet.getUsuarios();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuariosResponse, response.getBody());
    }

    @Test
    void getOneUser_existingUser_returnsUsuarioResponse() {
        Long userId = 1L;
        Usuario usuario = new Usuario("Rafael", LocalDate.of(1994, 2, 28), "123.454.789-10", "rafa@teste.com", "123456789");
        usuario.setId(1L);
        UsuarioResponse expectedResponse = new UsuarioResponse(userId, usuario.getNome(), usuario.getAniversario(), usuario.getCpf(), usuario.getEmail(), usuario.getTelefone());

        when(usuarioService.getOneUser(userId)).thenReturn(usuario);
//        when(usuarioMapper.mapToUsuarioResponse(usuario)).thenReturn(expectedResponse);

        ResponseEntity<UsuarioResponse> response = usuarioControllerGet.pegarUmUsuario(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void getUsuario_nonExistingUser() {
        Long userId = 1L;

        when(usuarioService.getOneUser(userId)).thenReturn(null);

        ResponseEntity<UsuarioResponse> response = usuarioControllerGet.pegarUmUsuario(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }
}

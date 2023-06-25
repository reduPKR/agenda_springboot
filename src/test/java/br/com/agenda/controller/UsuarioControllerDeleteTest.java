package br.com.agenda.controller;

import br.com.agenda.exceptions.UsuarioNotFoundException;
import br.com.agenda.mapper.UsuarioMapper;
import br.com.agenda.model.Usuario;
import br.com.agenda.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerDeleteTest {
    @InjectMocks
    private UsuarioControllerDelete usuarioControllerDelete;
    @Mock
    private UsuarioService usuarioService;

    @Test
    public void deleteUsuario_existingUser_returnsOkResponse() {
        Long id = 1L;
        doNothing().when(usuarioService).deleteUsuario(id);

        ResponseEntity<String> response = usuarioControllerDelete.deleteUsuario(id);

        verify(usuarioService).deleteUsuario(id);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteUsuario_nonExistingUser_returnsNotFoundResponse() {
        Long id = 1L;
        doThrow(new UsuarioNotFoundException("Usuário não encontrado com o ID: " + id)).when(usuarioService).deleteUsuario(id);

        ResponseEntity<String> response = usuarioControllerDelete.deleteUsuario(id);
        verify(usuarioService).deleteUsuario(id);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
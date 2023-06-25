package br.com.agenda.controller;

import br.com.agenda.dto.UsuarioRequest;
import br.com.agenda.dto.UsuarioResponse;
import br.com.agenda.dto.UsuarioUpdateRequest;
import br.com.agenda.exceptions.UsuarioNotFoundException;
import br.com.agenda.mapper.UsuarioMapper;
import br.com.agenda.model.Usuario;
import br.com.agenda.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerUpdateTest {
    @InjectMocks
    private UsuarioControllerUpdate usuarioControllerUpdate;
    @Mock
    private UsuarioService usuarioService;
    @Mock
    private UsuarioMapper usuarioMapper;

    @Test
    void updateUsuario_patch_Success() {
        Long id = 1L;
        LocalDate data = LocalDate.of(1994, 2, 28);

        UsuarioUpdateRequest request = new UsuarioUpdateRequest();
        request.setNome("Eduardo");

        Usuario usuario = new Usuario("Rafael", data, "123.454.789-10", "rafa@teste.com", "123456789");
        usuario.setId(id);

        when(usuarioService.getUsuarioById(id)).thenReturn(usuario);
        when(usuarioMapper.updateUsuarioFromRequest(usuario, request)).thenReturn(usuario);
        when(usuarioService.updateUsuario(usuario)).thenReturn(usuario);
        when(usuarioMapper.mapUsuarioToResponse(usuario)).thenReturn(new UsuarioResponse());

        ResponseEntity<UsuarioResponse> response = usuarioControllerUpdate.patchUsuario(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateUsuario_patch_UserNotFound() {
        Long id = 1L;
        UsuarioUpdateRequest request = new UsuarioUpdateRequest();
        request.setNome("Eduardo");

        when(usuarioService.getUsuarioById(id)).thenThrow(new UsuarioNotFoundException("Usuario não encontrado"));

        assertThrows(UsuarioNotFoundException.class, () -> usuarioControllerUpdate.patchUsuario(id, request));
    }

    @Test
    void updateUsuario_put_Success() {
        Long id = 1L;
        UsuarioRequest request = new UsuarioRequest();
        request.setNome("Eduardo");

        LocalDate data = LocalDate.of(1994, 2, 28);
        Usuario usuario = new Usuario("Rafael", data, "123.454.789-10", "rafa@teste.com", "123456789");
        usuario.setId(id);

        when(usuarioMapper.mapUsuarioRequestToEntity(request)).thenReturn(usuario);
        when(usuarioService.updateUsuario(usuario)).thenReturn(usuario);
        when(usuarioMapper.mapUsuarioToResponse(usuario)).thenReturn(new UsuarioResponse());

        ResponseEntity<UsuarioResponse> response = usuarioControllerUpdate.putUsuario(id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateUsuario_put_UserNotFound() {
        Long id = 1L;
        UsuarioRequest request = new UsuarioRequest();
        request.setNome("Eduardo");

        LocalDate data = LocalDate.of(1994, 2, 28);
        Usuario usuario = new Usuario("Rafael", data, "123.454.789-10", "rafa@teste.com", "123456789");
        usuario.setId(id);

        when(usuarioMapper.mapUsuarioRequestToEntity(request)).thenReturn(usuario);
        when(usuarioService.updateUsuario(usuario)).thenThrow(new UsuarioNotFoundException("usuario não encontrado"));

        assertThrows(UsuarioNotFoundException.class, () -> usuarioControllerUpdate.putUsuario(id, request));
    }
}
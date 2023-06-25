package br.com.agenda.mapper;

import br.com.agenda.dto.UsuarioRequest;
import br.com.agenda.dto.UsuarioResponse;
import br.com.agenda.dto.UsuarioUpdateRequest;
import br.com.agenda.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioMapperTest {
    @InjectMocks
    private UsuarioMapper usuarioMapper;

    @Test
    void mapToUsuario() {
        LocalDate data = LocalDate.of(1994,2,28);
        UsuarioRequest request = new UsuarioRequest("Rafael", data, "123.454.789-10","rafa@teste.com","123456789");

        Usuario usuario = usuarioMapper.mapToUsuario(request);
        assertEquals(request.getNome(), usuario.getNome());
        assertEquals(request.getAniversario(), usuario.getAniversario());
        assertEquals(request.getCpf(), usuario.getCpf());
        assertEquals(request.getEmail(), usuario.getEmail());
        assertEquals(request.getTelefone(), usuario.getTelefone());
    }

    @Test
    void mapToUsuarioResponse() {
        LocalDate data = LocalDate.of(1994,2,28);
        Usuario usuario = new Usuario("Rafael", data, "123.454.789-10","rafa@teste.com","123456789");

        UsuarioResponse usuarioResponse = usuarioMapper.mapUsuarioToResponse(usuario);
        assertEquals(usuario.getId(), usuarioResponse.getId());
        assertEquals(usuario.getNome(), usuarioResponse.getNome());
        assertEquals(usuario.getAniversario(), usuarioResponse.getAniversario());
        assertEquals(usuario.getCpf(), usuarioResponse.getCpf());
        assertEquals(usuario.getEmail(), usuarioResponse.getEmail());
        assertEquals(usuario.getTelefone(), usuarioResponse.getTelefone());
    }

    @Test
    void mapToUsuarioResponseList() {
        LocalDate data = LocalDate.of(1994, 2, 28);
        Usuario usuario1 = new Usuario("Rafael", data, "123.454.789-10", "rafa@teste.com", "123456789");
        Usuario usuario2 = new Usuario("Maria", data, "987.654.321-10", "maria@teste.com", "987654321");
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        List<UsuarioResponse> usuarioResponses = usuarioMapper.mapToUsuarioResponseList(usuarios);
        assertEquals(usuarios.size(), usuarioResponses.size());

        UsuarioResponse response1 = usuarioResponses.get(0);
        UsuarioResponse response2 = usuarioResponses.get(1);

        assertEquals(usuario1.getId(), response1.getId());
        assertEquals(usuario1.getNome(), response1.getNome());
        assertEquals(usuario1.getAniversario(), response1.getAniversario());
        assertEquals(usuario1.getCpf(), response1.getCpf());
        assertEquals(usuario1.getEmail(), response1.getEmail());
        assertEquals(usuario1.getTelefone(), response1.getTelefone());

        assertEquals(usuario2.getId(), response2.getId());
        assertEquals(usuario2.getNome(), response2.getNome());
        assertEquals(usuario2.getAniversario(), response2.getAniversario());
        assertEquals(usuario2.getCpf(), response2.getCpf());
        assertEquals(usuario2.getEmail(), response2.getEmail());
        assertEquals(usuario2.getTelefone(), response2.getTelefone());
    }

    @Test
    void updateUsuarioFromRequest() {
        LocalDate data = LocalDate.of(1994, 2, 28);
        Usuario usuario = new Usuario("Rafael", data, "123.454.789-10", "rafa@teste.com", "123456789");

        LocalDate data2 = LocalDate.of(1994, 3, 01);
        UsuarioUpdateRequest request = new UsuarioUpdateRequest();
        request.setNome("Eduardo");
        request.setAniversario(data2);
        request.setCpf("456.789.123.10");
        request.setEmail("eduardo@teste.com");
        request.setTelefone("123456789");

        Usuario updatedUsuario = usuarioMapper.updateUsuarioFromRequest(usuario, request);

        assertEquals(request.getNome(), updatedUsuario.getNome());
        assertEquals(request.getAniversario(), updatedUsuario.getAniversario());
        assertEquals(request.getCpf(), updatedUsuario.getCpf());
        assertEquals(request.getEmail(), updatedUsuario.getEmail());
        assertEquals(request.getTelefone(), updatedUsuario.getTelefone());
    }

    @Test
    void mapUsuarioRequestToEntity() {
        LocalDate data = LocalDate.of(1994, 2, 28);
        UsuarioRequest request = new UsuarioRequest("Rafael", data, "123.454.789-10", "rafa@teste.com", "123456789");

        Usuario usuario = usuarioMapper.mapUsuarioRequestToEntity(request);
        assertEquals(request.getNome(), usuario.getNome());
        assertEquals(request.getEmail(), usuario.getEmail());
        assertNull(usuario.getAniversario());
        assertNull(usuario.getCpf());
        assertNull(usuario.getTelefone());
    }
}
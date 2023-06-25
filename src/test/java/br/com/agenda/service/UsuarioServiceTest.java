package br.com.agenda.service;

import br.com.agenda.exceptions.UsuarioNotFoundException;
import br.com.agenda.model.Usuario;
import br.com.agenda.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    void createUsuario() {
        LocalDate data = LocalDate.of(1994,2,28);
        Usuario usuario = new Usuario("Rafael", data, "123.454.789-10","rafa@teste.com","123456789");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario novoUsuario = usuarioService.createUsuario(usuario);

        assertEquals(usuario, novoUsuario);
    }

    @Test
    void getOneUser_nonExistingUser_returnsNull() {
        Long userId = 1L;
        when(usuarioRepository.findById(userId)).thenReturn(Optional.empty());

        Usuario result = usuarioService.getOneUser(userId);

        assertNull(result);
    }

    @Test
    void getUsuarios_multipleUsers_returnsListOfUsers() {
        List<Usuario> usuarios = Arrays.asList(
                new Usuario("Rafael", LocalDate.of(1994, 2, 28), "123.454.789-10",
                        "rafa@teste.com", "123456789"),
                new Usuario("João", LocalDate.of(1990, 6, 15), "987.654.321-00",
                        "joao@teste.com", "987654321")
        );
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.getUsuarios();

        assertEquals(usuarios, result);
    }

    @Test
    public void deleteUsuario_existingUser_deletesUser() throws UsuarioNotFoundException {
        Long id = 1L;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuario));
        doNothing().when(usuarioRepository).delete(usuario);

        usuarioService.deleteUsuario(id);

        verify(usuarioRepository).findById(id);
        verify(usuarioRepository).delete(usuario);
    }

    @Test
    public void deleteUsuario_nonExistingUser_throwsException() {
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(UsuarioNotFoundException.class, () -> {
            usuarioService.deleteUsuario(id);
        });
    }

    @Test
    public void updateUsuario_existingUser_updatesUser() throws UsuarioNotFoundException {
        Long id = 1L;
        Usuario existingUsuario = new Usuario();
        existingUsuario.setId(id);

        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setId(id);
        updatedUsuario.setNome("Novo Nome");
        updatedUsuario.setEmail("novoemail@teste.com");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(existingUsuario));
        when(usuarioRepository.save(existingUsuario)).thenReturn(updatedUsuario);

        Usuario result = usuarioService.updateUsuario(updatedUsuario);

        assertEquals(updatedUsuario, result);
        verify(usuarioRepository).findById(id);
        verify(usuarioRepository).save(existingUsuario);
    }

    @Test
    public void updateUsuario_nonExistingUser_throwsException() {
        Long id = 1L;
        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setId(id);
        updatedUsuario.setNome("Novo Nome");
        updatedUsuario.setEmail("novoemail@teste.com");

        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(UsuarioNotFoundException.class, () -> {
            usuarioService.updateUsuario(updatedUsuario);
        });
    }

}
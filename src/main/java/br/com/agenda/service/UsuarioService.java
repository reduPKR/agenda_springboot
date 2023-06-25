package br.com.agenda.service;

import br.com.agenda.exceptions.UsuarioNotFoundException;
import br.com.agenda.model.Usuario;
import br.com.agenda.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getOneUser(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        return optionalUsuario.orElse(null);
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado com o ID: " + id));

        usuarioRepository.delete(usuario);
    }

    public Usuario getUsuarioById(Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));
    }

    public Usuario updateUsuario(Usuario usuario) throws UsuarioNotFoundException {
        Usuario existingUsuario = getUsuarioById(usuario.getId());
        BeanUtils.copyProperties(usuario, existingUsuario, "id");
        return usuarioRepository.save(existingUsuario);
    }
}

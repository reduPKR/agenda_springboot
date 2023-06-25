package br.com.agenda.mapper;

import br.com.agenda.dto.UsuarioRequest;
import br.com.agenda.dto.UsuarioResponse;
import br.com.agenda.dto.UsuarioUpdateRequest;
import br.com.agenda.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper {

    public Usuario mapToUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario(usuarioRequest.getNome(), usuarioRequest.getAniversario(),
                usuarioRequest.getCpf(), usuarioRequest.getEmail(), usuarioRequest.getTelefone());
        return usuario;
    }

    public UsuarioResponse mapUsuarioToResponse(Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse(usuario.getId(), usuario.getNome(),
                usuario.getAniversario(), usuario.getCpf(), usuario.getEmail(),
                usuario.getTelefone());
        return usuarioResponse;
    }

    public List<UsuarioResponse> mapToUsuarioResponseList(List<Usuario> usuarios) {
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioResponse usuarioResponse = mapUsuarioToResponse(usuario);
            usuarioResponses.add(usuarioResponse);
        }
        return usuarioResponses;
    }

    public Usuario updateUsuarioFromRequest(Usuario usuario, UsuarioUpdateRequest request) {
        if (request.getNome() != null) {
            usuario.setNome(request.getNome());
        }
        if (request.getAniversario() != null) {
            usuario.setAniversario(request.getAniversario());
        }
        if (request.getCpf() != null) {
            usuario.setCpf(request.getCpf());
        }
        if (request.getEmail() != null) {
            usuario.setEmail(request.getEmail());
        }
        if (request.getTelefone() != null) {
            usuario.setTelefone(request.getTelefone());
        }
        return usuario;
    }


    public Usuario mapUsuarioRequestToEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        return usuario;
    }
}


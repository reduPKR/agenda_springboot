package br.com.agenda.controller;

import br.com.agenda.dto.UsuarioRequest;
import br.com.agenda.dto.UsuarioResponse;
import br.com.agenda.dto.UsuarioUpdateRequest;
import br.com.agenda.exceptions.UsuarioNotFoundException;
import br.com.agenda.mapper.UsuarioMapper;
import br.com.agenda.model.Usuario;
import br.com.agenda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControllerUpdate {
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioControllerUpdate(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponse> patchUsuario(@PathVariable Long id, @RequestBody UsuarioUpdateRequest updateRequest)
            throws UsuarioNotFoundException {
        Usuario usuario = usuarioService.getUsuarioById(id);
        usuarioMapper.updateUsuarioFromRequest(usuario, updateRequest);
        Usuario updatedUsuario = usuarioService.updateUsuario(usuario);
        UsuarioResponse response = usuarioMapper.mapUsuarioToResponse(updatedUsuario);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> putUsuario(@PathVariable Long id, @RequestBody UsuarioRequest request)
            throws UsuarioNotFoundException {
        Usuario usuario = usuarioMapper.mapUsuarioRequestToEntity(request);
        usuario.setId(id);
        Usuario updatedUsuario = usuarioService.updateUsuario(usuario);
        UsuarioResponse response = usuarioMapper.mapUsuarioToResponse(updatedUsuario);
        return ResponseEntity.ok(response);
    }
}

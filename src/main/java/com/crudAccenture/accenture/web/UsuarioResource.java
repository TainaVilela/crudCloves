package com.crudAccenture.accenture.web;

import com.crudAccenture.accenture.repository.UsuarioRepository;
import com.crudAccenture.accenture.service.UsuarioService;
import com.crudAccenture.accenture.service.dto.UsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioResource extends BaseResource {

    private final Logger log = LoggerFactory.getLogger(UsuarioResource.class);

    private static final String ENTITY_NAME = "usuario";

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioResource(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) throws URISyntaxException {
        log.debug("REST request to save Usuario : {}", usuarioDTO);
        if (usuarioDTO.getId() != null) {
            throw new IllegalArgumentException("id:Novo usuário não tem ID");
        }
        UsuarioDTO result = null;
        try {
            result = usuarioService.save(usuarioDTO);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Message:Possivelmente este usuário já existe, verifique o login e demais informações sensíveis");
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ENTITY_NAME, result.getId().toString());
        return ResponseEntity
                .created(new URI("/api/usuarios/" + result.getId()))
                .headers(responseHeaders)
                .body(result);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody UsuarioDTO usuarioDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Usuario : {}, {}", id, usuarioDTO);
        if (usuarioDTO.getId() == null) {
            throw new IllegalArgumentException("Message: Id inválido");
        }
        if (!Objects.equals(id, usuarioDTO.getId())) {
            throw new IllegalArgumentException("Message: Id inválido");
        }

        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Message: Usuário não encontrado");
        }

        UsuarioDTO result = usuarioService.save(usuarioDTO);

        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/usuarios")
    public List<UsuarioDTO> getAllUsuarios() {
        log.debug("REST request to get all Usuarios");
        return usuarioService.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Long id) {
        log.debug("REST request to get Usuario : {}", id);
        Optional<UsuarioDTO> usuarioDTO = usuarioService.findOne(id);
        if (usuarioDTO.isPresent()) {
            return ResponseEntity.ok(usuarioDTO.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        log.debug("REST request to delete Usuario : {}", id);
        try {
            usuarioService.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new IllegalArgumentException("Message:Usuário não encontrado");
        }
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/usuarios/search")
    public List<UsuarioDTO> searchUsuarios(@RequestParam String query) {
        log.debug("REST request to search Usuarios for query {}", query);
        return usuarioService.search(query);
    }
}



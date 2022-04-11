package com.crudAccenture.accenture.service.impl;

import com.crudAccenture.accenture.domain.Usuario;
import com.crudAccenture.accenture.repository.UsuarioRepository;
import com.crudAccenture.accenture.service.UsuarioService;
import com.crudAccenture.accenture.service.dto.UsuarioDTO;
import com.crudAccenture.accenture.service.mapper.UsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            UsuarioMapper usuarioMapper
    ) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        log.debug("Request to save Usuario : {}", usuarioDTO);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        UsuarioDTO result = usuarioMapper.toDto(usuario);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        log.debug("Request to get all Usuarios");
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findOne(Long id) {
        log.debug("Request to get Usuario : {}", id);
        return usuarioRepository.findById(id).map(usuarioMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Usuario : {}", id);
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> search(String query) {
        log.debug("Request to search Usuarios for query {}", query);
        String[] parameters = query.split(" ");

        String name = null;
        String email = null;

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].contains("nome")) {
                name = parameters[i].split(":")[1];
            }
            if (parameters[i].contains("email")) {
                email = parameters[i].split(":")[1];
            }
        }

        return usuarioRepository.findByNomeOrEmail(name, email).map(usuarioMapper::toDto).get();
    }
}

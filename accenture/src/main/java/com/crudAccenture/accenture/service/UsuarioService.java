package com.crudAccenture.accenture.service;

 import com.crudAccenture.accenture.service.dto.UsuarioDTO;
 import java.util.List;
 import java.util.Optional;

public interface UsuarioService {
    UsuarioDTO save(UsuarioDTO usuarioDTO);
    List<UsuarioDTO> findAll();
    Optional<UsuarioDTO> findOne(Long id);
    void delete(Long id);
    List<UsuarioDTO> search(String query);
}


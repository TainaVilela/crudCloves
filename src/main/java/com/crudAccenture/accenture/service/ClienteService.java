package com.crudAccenture.accenture.service;

import com.crudAccenture.accenture.service.dto.ClienteDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    ClienteDTO save(ClienteDTO clienteDTO);
    List<ClienteDTO> findAll();
    Optional<ClienteDTO> findOne(Long id);
    void delete(Long id);
    List<ClienteDTO> search(String query);
}


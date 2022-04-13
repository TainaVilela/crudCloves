package com.crudAccenture.accenture.service;

import com.crudAccenture.accenture.service.dto.LivroCaixaDTO;

import java.util.List;
import java.util.Optional;

public interface LivroCaixaService {
    LivroCaixaDTO save(LivroCaixaDTO livroCaixaDTO);
    List<LivroCaixaDTO> findAll();
    Optional<LivroCaixaDTO> findOne(Long id);
    void delete(Long id);
    List<LivroCaixaDTO> search(String query);
}

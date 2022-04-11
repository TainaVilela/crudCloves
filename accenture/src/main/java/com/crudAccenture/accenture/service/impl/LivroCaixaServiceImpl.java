package com.crudAccenture.accenture.service.impl;

import com.crudAccenture.accenture.domain.LivroCaixa;
import com.crudAccenture.accenture.repository.LivroCaixaRepository;
import com.crudAccenture.accenture.service.LivroCaixaService;
import com.crudAccenture.accenture.service.dto.LivroCaixaDTO;
import com.crudAccenture.accenture.service.mapper.LivroCaixaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class LivroCaixaServiceImpl implements LivroCaixaService {

    private final Logger log = LoggerFactory.getLogger(LivroCaixaServiceImpl.class);

    private final LivroCaixaRepository livroCaixaRepository;
    private final LivroCaixaMapper livroCaixaMapper;

    public LivroCaixaServiceImpl(
            LivroCaixaRepository livroCaixaRepository,
            LivroCaixaMapper livroCaixaMapper
    ) {
        this.livroCaixaRepository = livroCaixaRepository;
        this.livroCaixaMapper = livroCaixaMapper;
    }

    @Override
    public LivroCaixaDTO save(LivroCaixaDTO livroCaixaDTO) {
        log.debug("Request to save LivroCaixa : {}", livroCaixaDTO);
        LivroCaixa livroCaixa = livroCaixaMapper.toEntity(livroCaixaDTO);
        livroCaixa = livroCaixaRepository.save(livroCaixa);
        LivroCaixaDTO result = livroCaixaMapper.toDto(livroCaixa);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroCaixaDTO> findAll() {
        log.debug("Request to get all LivroCaixas");
        return livroCaixaRepository.findAll().stream().map(livroCaixaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LivroCaixaDTO> findOne(Long id) {
        log.debug("Request to get LivroCaixa : {}", id);
        return livroCaixaRepository.findById(id).map(livroCaixaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LivroCaixa : {}", id);
        livroCaixaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LivroCaixaDTO> search(String query) {
        log.debug("Request to search Clientes for query {}", query);
        Collection<LivroCaixaDTO> allLivroCaixa = new LinkedHashSet<LivroCaixaDTO>();

        String clientId = null;
        Optional<List<LivroCaixa>> livroCaixaByClientId = null;

        if (query.contains("clientId")) {
            clientId = query.split(":")[1];
            livroCaixaByClientId = livroCaixaRepository.findByClienteId(Long.parseLong(clientId));
            if (livroCaixaByClientId.isPresent()) {
                allLivroCaixa.addAll(livroCaixaByClientId.map(livroCaixaMapper::toDto).get());
            }
        }
        return new ArrayList<LivroCaixaDTO>(allLivroCaixa);
    }
}


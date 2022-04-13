package com.crudAccenture.accenture.service.impl;

import com.crudAccenture.accenture.domain.Cliente;
import com.crudAccenture.accenture.repository.ClienteRepository;
import com.crudAccenture.accenture.repository.LivroCaixaRepository;
import com.crudAccenture.accenture.service.ClienteService;
import com.crudAccenture.accenture.service.dto.ClienteDTO;
import com.crudAccenture.accenture.service.mapper.ClienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final ClienteRepository clienteRepository;
    private final LivroCaixaRepository livroCaixaRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(
            ClienteRepository clienteRepository,
            LivroCaixaRepository livroCaixaRepository,
            ClienteMapper clienteMapper
    ) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.livroCaixaRepository = livroCaixaRepository;
    }

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        log.debug("Request to save Cliente : {}", clienteDTO);
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        ClienteDTO result = clienteMapper.toDto(cliente);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        log.debug("Request to get all Clientes");
        return clienteRepository.findAll().stream().map(clienteMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> findOne(Long id) {
        log.debug("Request to get Cliente : {}", id);
        return clienteRepository.findById(id).map(clienteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cliente : {}", id);
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> search(String query) {
        log.debug("Request to search Clientes for query {}", query);
        String[] parameters = query.split(" ");

        String name = null;
        String cpfCnpj = null;
        String city = null;
        String uf = null;

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].contains("nome")) {
                name = parameters[i].split(":")[1];
            }
            if (parameters[i].contains("cpfCnpj")) {
                cpfCnpj = parameters[i].split(":")[1];
            }
            if (parameters[i].contains("cidade")) {
                city = parameters[i].split(":")[1];
            }
            if (parameters[i].contains("uf")) {
                uf = parameters[i].split(":")[1];
            }
        }
        return clienteRepository.findByNomeOrCpfCnpjOrCidadeOrUf(name, cpfCnpj, city, uf).map(clienteMapper::toDto).get();
    }

    private LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date,formatter);
    }
}



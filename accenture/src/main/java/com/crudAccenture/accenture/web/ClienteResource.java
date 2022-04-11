package com.crudAccenture.accenture.web;

import com.crudAccenture.accenture.repository.ClienteRepository;
import com.crudAccenture.accenture.service.ClienteService;
import com.crudAccenture.accenture.service.dto.ClienteDTO;
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
public class ClienteResource extends BaseResource {

    private final Logger log = LoggerFactory.getLogger(ClienteResource.class);

    private static final String ENTITY_NAME = "cliente";

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    public ClienteResource(ClienteService clienteService, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) throws URISyntaxException {
        log.debug("REST request to save Cliente : {}", clienteDTO);
        if (clienteDTO.getId() != null) {
            throw new IllegalArgumentException("id:Novo cliente não tem ID");
        }
        ClienteDTO result = null;
        try {
            result = clienteService.save(clienteDTO);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Message:Possivelmente este cliente já existe, verifique o cpf/cnpj e demais informações sensíveis");
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ENTITY_NAME, result.getId().toString());
        return ResponseEntity
                .created(new URI("/api/clientes/" + result.getId()))
                .headers(responseHeaders)
                .body(result);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody ClienteDTO clienteDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Cliente : {}, {}", id, clienteDTO);
        if (clienteDTO.getId() == null) {
            throw new IllegalArgumentException("Message: Id inválido");
        }
        if (!Objects.equals(id, clienteDTO.getId())) {
            throw new IllegalArgumentException("Message: Id inválido");
        }

        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Message: Cliente não encontrado");
        }

        ClienteDTO result = clienteService.save(clienteDTO);

        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/clientes")
    public List<ClienteDTO> getAllClientes() {
        log.debug("REST request to get all Clientes");
        return clienteService.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable Long id) {
        log.debug("REST request to get Cliente : {}", id);
        Optional<ClienteDTO> clienteDTO = clienteService.findOne(id);
        if (clienteDTO.isPresent()) {
            return ResponseEntity.ok(clienteDTO.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        log.debug("REST request to delete Cliente : {}", id);
        try {
            clienteService.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new IllegalArgumentException("Message:Cliente não encontrado");
        }
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/clientes/search")
    public List<ClienteDTO> searchClientes(@RequestParam String query) throws IllegalArgumentException{
        log.debug("REST request to search Clientes for query {}", query);
        List<ClienteDTO> cliente = null;
        try {
            cliente = clienteService.search(query);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return cliente;
    }
}

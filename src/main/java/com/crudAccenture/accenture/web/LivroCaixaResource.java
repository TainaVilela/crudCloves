package com.crudAccenture.accenture.web;

import com.crudAccenture.accenture.repository.LivroCaixaRepository;
import com.crudAccenture.accenture.service.LivroCaixaService;
import com.crudAccenture.accenture.service.dto.LivroCaixaDTO;
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
public class LivroCaixaResource extends BaseResource {

    private final Logger log = LoggerFactory.getLogger(LivroCaixaResource.class);

    private static final String ENTITY_NAME = "livroCaixa";

    private final LivroCaixaService livroCaixaService;
    private final LivroCaixaRepository livroCaixaRepository;

    public LivroCaixaResource(LivroCaixaService livroCaixaService, LivroCaixaRepository livroCaixaRepository) {
        this.livroCaixaService = livroCaixaService;
        this.livroCaixaRepository = livroCaixaRepository;
    }

    @PostMapping("/livros-caixa")
    public ResponseEntity<LivroCaixaDTO> createLivroCaixa(@Valid @RequestBody LivroCaixaDTO livroCaixaDTO) throws URISyntaxException {
        log.debug("REST request to save LivroCaixa : {}", livroCaixaDTO);
        if (livroCaixaDTO.getId() != null) {
            throw new IllegalArgumentException("id:Novo Livro Caixa não tem ID");
        }
        LivroCaixaDTO result = null;
        try {
            result = livroCaixaService.save(livroCaixaDTO);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("Message:Possivelmente este dado já existe, verifique o login e demais informações sensíveis");
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ENTITY_NAME, result.getId().toString());
        return ResponseEntity
                .created(new URI("/api/livros-caixa/" + result.getId()))
                .headers(responseHeaders)
                .body(result);
    }

    @PutMapping("/livros-caixa/{id}")
    public ResponseEntity<LivroCaixaDTO> updateLivroCaixa(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody LivroCaixaDTO livroCaixaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LivroCaixa : {}, {}", id, livroCaixaDTO);
        if (livroCaixaDTO.getId() == null) {
            throw new IllegalArgumentException("Message: Id inválido");
        }
        if (!Objects.equals(id, livroCaixaDTO.getId())) {
            throw new IllegalArgumentException("Message: Id inválido");
        }

        if (!livroCaixaRepository.existsById(id)) {
            throw new IllegalArgumentException("Message: Livro Caixa não encontrado");
        }

        LivroCaixaDTO result = livroCaixaService.save(livroCaixaDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/livros-caixa")
    public List<LivroCaixaDTO> getAllLivroCaixas() {
        log.debug("REST request to get all LivroCaixas");
        return livroCaixaService.findAll();
    }

    @GetMapping("/livros-caixa/{id}")
    public ResponseEntity<LivroCaixaDTO> getLivroCaixa(@PathVariable Long id) {
        log.debug("REST request to get LivroCaixa : {}", id);
        Optional<LivroCaixaDTO> livroCaixaDTO = livroCaixaService.findOne(id);
        if(livroCaixaDTO.isPresent()){
            return ResponseEntity.ok(livroCaixaDTO.get());
        }else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @DeleteMapping("/livros-caixa/{id}")
    public ResponseEntity<Void> deleteLivroCaixa(@PathVariable Long id) {
        log.debug("REST request to delete LivroCaixa : {}", id);
        try {
            livroCaixaService.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new IllegalArgumentException("Message:Registro não encontrado");
        }
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/livros-caixa/search")
    public List<LivroCaixaDTO> searchLivroCaixas(@Valid @RequestParam String query) {
        log.debug("REST request to search LivroCaixas for query {}", query);
        return livroCaixaService.search(query);
    }
}


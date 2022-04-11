package com.crudAccenture.accenture.repository;

import com.crudAccenture.accenture.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<List<Cliente>> findByNomeOrCpfCnpjOrCidadeOrUf(String nome, String cpjCnpj, String cidade, String uf);
}

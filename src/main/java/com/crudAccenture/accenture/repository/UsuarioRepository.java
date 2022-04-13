package com.crudAccenture.accenture.repository;

import com.crudAccenture.accenture.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<List<Usuario>> findByNomeOrEmail(String nome, String email);
}


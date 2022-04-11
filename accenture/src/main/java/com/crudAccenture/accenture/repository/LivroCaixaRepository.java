package com.crudAccenture.accenture.repository;

import com.crudAccenture.accenture.domain.LivroCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface LivroCaixaRepository extends JpaRepository<LivroCaixa, Long> {
    Optional<List<LivroCaixa>> findByClienteId(Long clientId);
    Optional<List<LivroCaixa>> findByClienteIdAndDataLancamentoBetweenOrderByDataLancamento(Long clientId, LocalDate startDate, LocalDate endDate);
}

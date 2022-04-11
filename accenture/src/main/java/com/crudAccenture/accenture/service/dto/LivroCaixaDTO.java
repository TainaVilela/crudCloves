package com.crudAccenture.accenture.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

public class LivroCaixaDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dataLancamento;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String descricao;

    @NotNull
    @NotEmpty
    @Size(max = 1)
    @Pattern(regexp = "D|C")
    private String tipo;

    @NotNull
    private BigDecimal valor;

    private ClienteDTO cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroCaixaDTO that = (LivroCaixaDTO) o;
        return dataLancamento.equals(that.dataLancamento) && descricao.equals(that.descricao) && tipo.equals(that.tipo) && valor.equals(that.valor) && cliente.equals(that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataLancamento, descricao, tipo, valor, cliente);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LivroCaixaDTO{" +
                "id=" + getId() +
                ", dataLancamento='" + getDataLancamento() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", tipo='" + getTipo() + "'" +
                ", valor=" + getValor() +
                ", cliente=" + getCliente() +
                "}";
    }
}


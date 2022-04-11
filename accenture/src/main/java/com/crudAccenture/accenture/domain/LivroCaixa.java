package com.crudAccenture.accenture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "livro_caixa")
public class LivroCaixa implements Serializable, Comparable<LivroCaixa>  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @NotNull
    @Size(max = 50)
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;

    @NotNull
    @Size(max = 1)
    @Pattern(regexp = "D|C")
    @Column(name = "tipo", length = 1, nullable = false)
    private String tipo;

    @NotNull
    @Column(name = "valor", precision = 21, scale = 2, nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties(value = { "livrosCaixa" }, allowSetters = true)
    private Cliente cliente;

    public Long getId() {
        return this.id;
    }

    public LivroCaixa id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataLancamento() {
        return this.dataLancamento;
    }

    public LivroCaixa dataLancamento(LocalDate dataLancamento) {
        this.setDataLancamento(dataLancamento);
        return this;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public LivroCaixa descricao(String descricao) {
        this.setDescricao(descricao);
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return this.tipo;
    }

    public LivroCaixa tipo(String tipo) {
        this.setTipo(tipo);
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public LivroCaixa valor(BigDecimal valor) {
        this.setValor(valor);
        return this;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LivroCaixa cliente(Cliente cliente) {
        this.setCliente(cliente);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroCaixa that = (LivroCaixa) o;
        return id.equals(that.id) && dataLancamento.equals(that.dataLancamento) && descricao.equals(that.descricao) && tipo.equals(that.tipo) && valor.equals(that.valor) && cliente.equals(that.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataLancamento, descricao, tipo, valor, cliente);
    }

    @Override
    public int compareTo(LivroCaixa comparable) {
        if (this.dataLancamento.compareTo(comparable.dataLancamento) > 0) return 1;
        else return 0;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LivroCaixa{" +
                "id=" + getId() +
                ", dataLancamento='" + getDataLancamento() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", tipo='" + getTipo() + "'" +
                ", valor=" + getValor() +
                "}";
    }
}


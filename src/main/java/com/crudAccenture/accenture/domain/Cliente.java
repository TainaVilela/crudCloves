package com.crudAccenture.accenture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @NotNull
    @Size(max = 30)
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @NotNull
    @Size(max = 14)
    @Pattern(regexp = "^[0-9]+$")
    @Column(name = "cpf_cnpj", length = 14, nullable = false, unique = true)
    private String cpfCnpj;

    @NotNull
    @Size(max = 50)
    @Column(name = "logradouro", length = 50, nullable = false)
    private String logradouro;

    @NotNull
    @Size(max = 40)
    @Column(name = "cidade", length = 40, nullable = false)
    private String cidade;

    @NotNull
    @Size(max = 2)
    @Column(name = "uf", length = 2, nullable = false)
    private String uf;

    @NotNull
    @Size(max = 8)
    @Column(name = "cep", length = 8, nullable = false)
    private String cep;

    @Size(max = 11)
    @Column(name = "telefone", length = 11)
    private String telefone;

    @Size(max = 100)
    @Pattern(regexp = "^$|[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")
    @Column(name = "email", length = 100)
    private String email;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnoreProperties(value = { "cliente" }, allowSetters = true)
    private Set<LivroCaixa> livrosCaixa = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public Cliente id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCadastro() {
        return this.dataCadastro;
    }

    public Cliente dataCadastro(LocalDate dataCadastro) {
        this.setDataCadastro(dataCadastro);
        return this;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return this.nome;
    }

    public Cliente nome(String nome) {
        this.setNome(nome);
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return this.cpfCnpj;
    }

    public Cliente cpfCnpj(String cpfCnpj) {
        this.setCpfCnpj(cpfCnpj);
        return this;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public Cliente logradouro(String logradouro) {
        this.setLogradouro(logradouro);
        return this;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return this.cidade;
    }

    public Cliente cidade(String cidade) {
        this.setCidade(cidade);
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return this.uf;
    }

    public Cliente uf(String uf) {
        this.setUf(uf);
        return this;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return this.cep;
    }

    public Cliente cep(String cep) {
        this.setCep(cep);
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public Cliente telefone(String telefone) {
        this.setTelefone(telefone);
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public Cliente email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<LivroCaixa> getLivrosCaixas() {
        return this.livrosCaixa;
    }

    public void setLivrosCaixas(Set<LivroCaixa> livroCaixas) {
        if (this.livrosCaixa != null) {
            this.livrosCaixa.forEach(i -> i.setCliente(null));
        }
        if (livroCaixas != null) {
            livroCaixas.forEach(i -> i.setCliente(this));
        }
        this.livrosCaixa = livroCaixas;
    }

    public Cliente livrosCaixas(Set<LivroCaixa> livroCaixas) {
        this.setLivrosCaixas(livroCaixas);
        return this;
    }

    public Cliente addLivrosCaixa(LivroCaixa livroCaixa) {
        this.livrosCaixa.add(livroCaixa);
        livroCaixa.setCliente(this);
        return this;
    }

    public Cliente removeLivrosCaixa(LivroCaixa livroCaixa) {
        this.livrosCaixa.remove(livroCaixa);
        livroCaixa.setCliente(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpfCnpj.equals(cliente.cpfCnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfCnpj);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                ", dataCadastro='" + getDataCadastro() + "'" +
                ", nome='" + getNome() + "'" +
                ", cpfCnpj='" + getCpfCnpj() + "'" +
                ", logradouro='" + getLogradouro() + "'" +
                ", cidade='" + getCidade() + "'" +
                ", uf='" + getUf() + "'" +
                ", cep='" + getCep() + "'" +
                ", telefone='" + getTelefone() + "'" +
                ", email='" + getEmail() + "'" +
                "}";
    }
}

package com.crudAccenture.accenture.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

public class ClienteDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dataCadastro;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String nome;

    @NotNull
    @NotEmpty
    @Size(max = 14)
    @Pattern(regexp = "^[0-9]+$")
    private String cpfCnpj;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String logradouro;

    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String cidade;

    @NotNull
    @NotEmpty
    @Size(max = 2)
    private String uf;

    @NotNull
    @NotEmpty
    @Size(max = 8)
    private String cep;

    @Size(max = 11)
    private String telefone;

    @Size(max = 100)
    @Pattern(regexp = "^$|[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteDTO that = (ClienteDTO) o;
        return cpfCnpj.equals(that.cpfCnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfCnpj);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClienteDTO{" +
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


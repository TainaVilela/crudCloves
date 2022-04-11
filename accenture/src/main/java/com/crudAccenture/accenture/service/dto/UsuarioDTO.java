package com.crudAccenture.accenture.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

public class UsuarioDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate dataCadastro;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String nome;

    @NotNull
    @NotEmpty
    @Size(max = 15)
    private String login;

    @NotNull
    @NotEmpty
    @Size(max = 10)
    private String senha;

    @Size(max = 11)
    private String telefone;

    @Size(max = 100)
    @Pattern(regexp = "^$|[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")
    private String email;

    @NotNull
    @Size(max = 1)
    @Pattern(regexp = "A|O")
    private String perfil;

    @NotNull
    @Size(max = 1)
    @Pattern(regexp = "A|C")
    private String status;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return login.equals(that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + getId() +
                ", dataCadastro='" + getDataCadastro() + "'" +
                ", nome='" + getNome() + "'" +
                ", login='" + getLogin() + "'" +
                ", senha='" + getSenha() + "'" +
                ", telefone='" + getTelefone() + "'" +
                ", email='" + getEmail() + "'" +
                ", perfil='" + getPerfil() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}

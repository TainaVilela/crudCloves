package com.crudAccenture.accenture.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

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
    @Size(max = 15)
    @Column(name = "login", length = 15, nullable = false, unique = true)
    private String login;

    @NotNull
    @Size(max = 10)
    @Column(name = "senha", length = 10, nullable = false)
    private String senha;

    @Size(max = 11)
    @Column(name = "telefone", length = 11)
    private String telefone;

    @Size(max = 100)
    @Pattern(regexp = "^$|[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")
    @Column(name = "email", length = 100)
    private String email;

    @NotNull
    @Size(max = 1)
    @Pattern(regexp = "A|O")
    @Column(name = "perfil", length = 1, nullable = false)
    private String perfil;

    @NotNull
    @Size(max = 1)
    @Pattern(regexp = "A|C")
    @Column(name = "status", length = 1, nullable = false)
    private String status;

    public Long getId() {
        return this.id;
    }

    public Usuario id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCadastro() {
        return this.dataCadastro;
    }

    public Usuario dataCadastro(LocalDate dataCadastro) {
        this.setDataCadastro(dataCadastro);
        return this;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return this.nome;
    }

    public Usuario nome(String nome) {
        this.setNome(nome);
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return this.login;
    }

    public Usuario login(String login) {
        this.setLogin(login);
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public Usuario senha(String senha) {
        this.setSenha(senha);
        return this;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public Usuario telefone(String telefone) {
        this.setTelefone(telefone);
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public Usuario email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return this.perfil;
    }

    public Usuario perfil(String perfil) {
        this.setPerfil(perfil);
        return this;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getStatus() {
        return this.status;
    }

    public Usuario status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return login.equals(usuario.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Usuario{" +
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
